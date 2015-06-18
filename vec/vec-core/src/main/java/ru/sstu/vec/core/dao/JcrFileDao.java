package ru.sstu.vec.core.dao;

import java.io.InputStream;
import java.util.List;

import javax.jcr.Node;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.JcrFile;
import ru.sstu.vec.core.util.JcrException;

/**
 * {@code JcrFileDao} interface is DAO to work with files ({@link JcrFile})
 * using JCR.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.1
 *
 */
public interface JcrFileDao {

    /**
     * Saves given file into repository as child of given parent Node.
     *
     * @param parent
     *            parent Node
     * @param fileData
     *            file data
     * @param stream
     *            stream from which to read the contents of the file
     * @return file as repository Node
     * @throws JcrException
     */
    public Node save(Node parent, JcrFile fileData, InputStream stream)
            throws JcrException;

    /**
     * Looking for file with given file name assigned to given Course.
     *
     * @param course
     *            course
     * @param name
     *            file name
     * @return file
     * @throws JcrException
     */
    public JcrFile find(Course course, String name) throws JcrException;

    /**
     * Returns all files for given Course. ( children of Course folder node)
     *
     * @param course
     *            course object
     * @return list of files assigned to given course
     * @throws JcrException
     */
    public List<JcrFile> find(Course course) throws JcrException;

    /**
     * Deletes file node with given file name assigned to folder node for given
     * Course.
     *
     * @param course
     *            course object
     * @param name
     *            file name
     * @throws JcrException
     */
    public void delete(Course course, String name) throws JcrException;

    /**
     * Deletes folder Node for given Course from repository and all its
     * children.
     *
     * @param course
     *            course object
     * @throws JcrException
     */
    public void delete(Course course) throws JcrException;

    /**
     * Checks if file with given name exists in folder for given Course.
     *
     * @param course
     *            course object
     * @param name
     *            file name
     * @throws JcrException
     */
    public boolean isFileExists(Course course, String name) throws JcrException;

    /**
     * Returns folder Node (<b>nt:folder</b>) for given Course. If folder does
     * not exist returns <b>null</b>.
     *
     * @param course
     *            course object
     * @return folder Node for given Course or <b>null</b>.
     * @throws JcrException
     */
    public Node getFolder(Course course) throws JcrException;

    /**
     * Returns folder Node (<b>nt:folder</b>) for given Course. If folder does
     * not exist, creates it.
     *
     * @param course
     * @return folder for given Course
     * @throws JcrException
     */
    public Node getOrCreateFolder(Course course) throws JcrException;

    /**
     * Returns folder Node (<b>nt:folder</b>) for given name, that is child of
     * given parent Node.
     *
     * @param parent
     *            parent Node
     * @param name
     *            folder name
     * @return
     * @throws JcrException
     */
    public Node getOrCreateFolder(Node parent, String name) throws JcrException;

}
