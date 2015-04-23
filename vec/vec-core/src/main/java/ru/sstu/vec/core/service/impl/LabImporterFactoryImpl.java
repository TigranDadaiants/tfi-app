package ru.sstu.vec.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ru.sstu.vec.core.service.LabImporter;
import ru.sstu.vec.core.service.LabImporterFactory;
import ru.sstu.vec.core.service.model.DocFileFormat;

/**
 * <code>LabImporterFactoryImpl</code> class is {@link LabImporterFactory}
 * implementation.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Service("labImporterFactory")
class LabImporterFactoryImpl implements LabImporterFactory {

	/**
	 * Lab importer resource.
	 */
	@Resource
	private LabImporter docXmlLabImporter;

	/**
	 * Lab importer resource.
	 */
	@Resource
	private LabImporter docxLabImporter;

	/**
	 * {@inheritDoc}
	 */
	public LabImporter get(DocFileFormat format) {
		Map<DocFileFormat, LabImporter> importers
				= new HashMap<DocFileFormat, LabImporter>();
		importers.put(DocFileFormat.DOC_XML, docXmlLabImporter);
		importers.put(DocFileFormat.DOCX, docxLabImporter);
		return importers.get(format);
	}
}
