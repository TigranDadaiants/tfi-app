package ru.sstu.vec.core.service;

import ru.sstu.vec.core.service.model.DocFileFormat;

/**
 * <code>LectureImporterFactory</code> interface is used for getting concrete
 * importer for given format.
 *
 * @author Tigran Dadaiants
 * @since VEC 2.1
 */
public interface LectureImporterFactory {

    /**
     * Provides importer for given format.
     *
     * @param format
     *            file format
     * @return importer
     */
    LectureImporter get(DocFileFormat format);

}
