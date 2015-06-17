package ru.sstu.vec.core.dao;

import java.io.InputStream;
import java.util.List;

import javax.jcr.Node;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.JcrFile;
import ru.sstu.vec.core.util.JcrException;

public interface JcrFileDao {

    public Node save(Node parent, JcrFile fileData, InputStream stream)
            throws JcrException;

    public JcrFile find(Course course, String name) throws JcrException;

    public List<JcrFile> find(Course course) throws JcrException;

    public void delete(Course course, String name) throws JcrException;

    public void delete(Course course) throws JcrException;

    public boolean isFileExists(Course course, String name) throws JcrException;

    public Node getFolder(Course course) throws JcrException;

    public Node getOrCreateFolder(Course course) throws JcrException;

    public Node getOrCreateFolder(Node parent, String name) throws JcrException;

}
