package ru.sstu.vec.core.service.impl;

import org.springframework.stereotype.Service;

import ru.sstu.docs.DocumentReader;
import ru.sstu.docs.DocxReader;

/**
 * <code>DocxLectureImporter</code> class is used for importing
 * <code>Lecture</code> data from MS Word 2007 format files.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.1
 */
@Service("docxLectureImporter")
public class DocxLectureImporter extends AbstractLectureImporter {

    @Override
    protected DocumentReader getReader() {
        return new DocxReader();
    }

}
