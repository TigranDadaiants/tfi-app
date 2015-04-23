package ru.sstu.vec.core.service;

import java.io.InputStream;

import ru.sstu.docs.DocumentException;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lecture;

/**
 * <code>LectureImporter</code> interface is used for importing {@link Lecture}
 * data from uploaded file.
 *
 * @author Tigran Dadaiants
 * @since VEC 2.1
 */
public interface LectureImporter {

    /**
     * Reads data from input stream. Creates {@link Lecture} object and stores
     * it in data base.
     *
     * @param course
     *            course
     * @param input
     *            input stream
     * @return imported lecture
     * @throws DocumentException
     *             if cannot read input stream
     */
    Lecture importLecture(Course course, InputStream input) throws DocumentException;

}
