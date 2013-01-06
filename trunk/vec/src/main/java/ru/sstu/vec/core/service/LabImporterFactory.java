package ru.sstu.vec.core.service;

import ru.sstu.vec.core.service.model.LabFileFormat;

/**
 * <code>LabImporterFactory</code> interface is used for getting concrete
 * importer for given format.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public interface LabImporterFactory {

	/**
	 * Provides importer for given format.
	 *
	 * @param format file format
	 * @return importer
	 */
	LabImporter get(LabFileFormat format);
}
