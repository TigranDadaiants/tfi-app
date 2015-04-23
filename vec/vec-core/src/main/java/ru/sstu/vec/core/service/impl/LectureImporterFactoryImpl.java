package ru.sstu.vec.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ru.sstu.vec.core.service.LectureImporter;
import ru.sstu.vec.core.service.LectureImporterFactory;
import ru.sstu.vec.core.service.model.DocFileFormat;

/**
 * <code>LectureImporterFactoryImpl</code> class is
 * {@link LectureImporterFactory} implementation.
 *
 * @author Tigran Dadaiants
 * @since VEC 2.1
 */
@Service("lectureImporterFactory")
public class LectureImporterFactoryImpl implements LectureImporterFactory {

    @Resource
    private LectureImporter docxLectureImporter;

    @Resource
    private LectureImporter docXmlLectureImporter;

    private Map<DocFileFormat, LectureImporter> importers = new HashMap<DocFileFormat, LectureImporter>();

    public LectureImporterFactoryImpl() {
        importers.put(DocFileFormat.DOC_XML, docXmlLectureImporter);
        importers.put(DocFileFormat.DOCX, docxLectureImporter);
    }

    @Override
    public LectureImporter get(DocFileFormat format) {
        return importers.get(format);
    }

}
