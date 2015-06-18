package ru.sstu.vec.core.service;

import java.io.InputStream;
import java.util.List;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.JcrFile;

/**
 * {@code JcrFileManager} interface describes methods for JcrFile objects
 * editing management.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.1
 */
public interface JcrFileManager {

    /**
     * @param course
     *            Course object
     * @return list of files for given Course
     */
    List<JcrFile> find(Course course);

    /**
     * Saves file as child of folder node for given Course.
     *
     * @param course
     *            course
     * @param file
     *            file to save
     * @param in
     *            InputStream to read file content
     */
    void save(Course course, JcrFile file, InputStream in);

    /**
     * Removes file with given name located in folder for given Course.
     *
     * @param course
     *            course
     * @param filename
     *            file name
     */
    void delete(Course course, String filename);

    /**
     * Removes folder for given Course.
     *
     * @param course
     */
    void delete(Course course);

}
