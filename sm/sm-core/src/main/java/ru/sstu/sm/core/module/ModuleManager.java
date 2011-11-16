package ru.sstu.sm.core.module;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.sstu.xml.DomUtil;
import ru.sstu.xml.XPathUtil;
import ru.sstu.xml.XmlException;

/**
 * <code>ModuleManager</code> class manages available modules.
 *
 * @author Denis_Murashev
 * @since SM 2.0
 */
public final class ModuleManager {

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(ModuleManager.class);

	/**
	 * Error message text.
	 */
	private static final String ERROR_INSTANTIATION
			= "Cannot instantiate class: ";

	/**
	 * Manager instance.
	 */
	private static ModuleManager instance = new ModuleManager();

	/**
	 * Modules.
	 */
	private final List<Module<?>> modules = new ArrayList<Module<?>>();

	/**
	 * Modules map.
	 */
	private final Map<String, Module<?>> map = new HashMap<String, Module<?>>();

	/**
	 * Current module.
	 */
	private Module<?> current;

	/**
	 * Initializes single instance.
	 */
	private ModuleManager() {
		try {
			Document document = DomUtil.open(getClass()
					.getResourceAsStream("/modules.xml"));
			String xPath = "/modules/module";
			for (Element element : XPathUtil.getElements(document, xPath)) {
				String id = element.getAttribute("id");
				String name = element.getTextContent();
				Module<?> module = createModule(name);
				if (module != null) {
					modules.add(module);
					map.put(id, module);
				}
			}
		} catch (XmlException e) {
			log.error("Cannot load modules descriptions", e);
		}
		if (modules.isEmpty()) {
			String error = "No modules loaded";
			log.fatal(error);
			throw new RuntimeException(error);
		}
		current = modules.get(0);
	}

	/**
	 * @param name module class name
	 * @return module if exists
	 */
	private Module<?> createModule(String name) {
		try {
			return (Module<?>) Class.forName(name).getConstructor()
					.newInstance();
		} catch (InstantiationException e) {
			log.error(ERROR_INSTANTIATION + name, e);
		} catch (IllegalAccessException e) {
			log.error("Cannot access class: " + name, e);
		} catch (ClassNotFoundException e) {
			log.error("Cannot find class: " + name, e);
		} catch (NoSuchMethodException e) {
			log.error(ERROR_INSTANTIATION + name, e);
		} catch (InvocationTargetException e) {
			log.error(ERROR_INSTANTIATION + name, e);
		}
		return null;
	}

	/**
	 * @return the instance
	 */
	public static ModuleManager getInstance() {
		return instance;
	}

	/**
	 * @return the current module
	 */
	public Module<?> getCurrent() {
		return current;
	}

	/**
	 * @param current the current module to set
	 */
	public void setCurrent(Module<?> current) {
		this.current = current;
	}

	/**
	 * @return all active modules
	 */
	public Iterable<Module<?>> getModules() {
		return modules;
	}

	/**
	 * @param id module id
	 * @return module
	 */
	public Module<?> getModule(String id) {
		return map.get(id);
	}
}
