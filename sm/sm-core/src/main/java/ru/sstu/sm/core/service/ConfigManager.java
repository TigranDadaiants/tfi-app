package ru.sstu.sm.core.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import org.w3c.dom.Node;
import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.module.Module;
import ru.sstu.sm.core.module.ModuleManager;
import ru.sstu.sm.core.util.TextUtil;
import ru.sstu.xml.DomUtil;
import ru.sstu.xml.XPathUtil;
import ru.sstu.xml.XmlException;
import ru.sstu.xml.XslUtil;

/**
 * <code>ConfigManager</code> class is abstract class for working with
 * configurations in XML files.
 *
 * @author Denis A. Murashev
 * @param <T> type of section
 * @since SM 2.0
 */
public class ConfigManager<T extends Section> {

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(ConfigManager.class);

	/**
	 * Error message.
	 */
	private static final String ERROR_SAVE = TextUtil.get("error.save");

	/**
	 * Error message.
	 */
	private static final String ERROR_LOAD = TextUtil.get("error.load");

	/**
	 * XSL scripts for previous versions.
	 */
	private static final Map<String, String> XSL
		= new HashMap<String, String>();

	static {
		XSL.put("2.0", "/xsl/sm-v2.0.xsl");
	}

	/**
	 * Configuration class.
	 */
	private final Class<? extends Config<T>> configClass;

	/**
	 * Initializes configuration manager.
	 *
	 * @param configClass configuration class
	 */
	public ConfigManager(Class<? extends Config<T>> configClass) {
		this.configClass = configClass;
	}

	/**
	 * Updates module.
	 *
	 * @param file file
	 * @throws SMException if some error occurs
	 */
	public static void updateModule(File file) throws SMException {
		try {
			FileInputStream input = new FileInputStream(file);
			Document document = DomUtil.open(input);
			input.close();
			String version = XPathUtil.getText(document, "/*/@version");
			if (!Config.VERSION.equals(version)) {
				document = convert(document, version);
			}
			String name = XPathUtil.getText(document, "/config/@module");
			Module<?> module = ModuleManager.getInstance().getModule(name);
			ModuleManager.getInstance().setCurrent(module);
			if (module != null) {
				ModuleManager.getInstance().getCurrent().getEngine()
						.loadConfig(document);
			}
		} catch (IOException e) {
			log.error(ERROR_LOAD, e);
			throw new SMException(e);
		} catch (XmlException e) {
			log.error(ERROR_LOAD, e);
			throw new SMException(e);
		}
	}

	/**
	 * Saves configuration.
	 *
	 * @param config configuration
	 * @param file   file
	 * @throws SMException if cannot save configuration
	 */
	public void save(Config<T> config, File file) throws SMException {
		try {
			JAXBContext context = JAXBContext.newInstance(getConfigClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(config, file);
		} catch (JAXBException e) {
			log.error(ERROR_SAVE, e);
			throw new SMException(e);
		}
	}

	/**
	 * Loads configuration from file.
	 *
	 * @param document document
	 * @return configuration
	 * @throws SMException if cannot load configuration
	 */
	@SuppressWarnings("unchecked")
	protected final Config<T> load(Node document) throws SMException {
		try {
			JAXBContext context = JAXBContext.newInstance(getConfigClass());
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (Config<T>) unmarshaller.unmarshal(document);
		} catch (JAXBException e) {
			log.error(ERROR_LOAD, e);
			throw new SMException(e);
		}
	}

	/**
	 * Provides new instance of configuration.
	 *
	 * @return empty configuration
	 */
	protected Class<? extends Config<T>> getConfigClass() {
		return configClass;
	}

	/**
	 * Converts given document from given to actual version.
	 *
	 * @param document document
	 * @param version  version
	 * @return converted document
	 * @throws SMException if some error occurs
	 */
	private static Document convert(Document document, String version)
			throws SMException {
		try {
			String path = XSL.get(version);
			if (path == null) {
				throw new SMException(TextUtil.get("error.version"));
			}
			Source xsl = new StreamSource(ConfigManager.class
					.getResourceAsStream(path));
			DOMResult result = new DOMResult(DomUtil.create());
			XslUtil.transform(new DOMSource(document), xsl, result);
			return (Document) result.getNode();
		} catch (XmlException e) {
			throw new SMException(e);
		}
	}
}
