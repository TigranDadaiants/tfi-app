package ru.sstu.vec.core.service.impl;

import org.springframework.stereotype.Service;

import ru.sstu.docs.DocXmlReader;
import ru.sstu.docs.DocumentReader;

/**
 * {@code DocXmlLecturemporter} class is used for importing {@code Lecture}
 * data from MS Word 2003 XML format files.
 *
 * @author Tigran Dadaiants
 * @since VEC 2.1
 */
@Service("docXmlLectureImporter")
public class DocXmlLectureImporter extends AbstractLectureImporter {

    @Override
    protected DocumentReader getReader() {
        return new DocXmlReader();
    }

}
