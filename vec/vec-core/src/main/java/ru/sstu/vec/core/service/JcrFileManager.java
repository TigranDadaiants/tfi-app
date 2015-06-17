package ru.sstu.vec.core.service;

import java.io.InputStream;
import java.util.List;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.JcrFile;

public interface JcrFileManager {

    List<JcrFile> find(Course course);

    void save(Course course, JcrFile file, InputStream in);

    void delete(Course course, String filename);

    void delete(Course course);

}
