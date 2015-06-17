package ru.sstu.vec.core.service.impl;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.JcrFileDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.JcrFile;
import ru.sstu.vec.core.service.JcrFileManager;

@Service("jcrFileManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class JcrFileManagerImpl implements JcrFileManager {

    @Resource
    private JcrFileDao jcrFileDao;

    @Override
    public List<JcrFile> find(Course course) {
        return jcrFileDao.find(course);
    }

    @Override
    @Transactional("atom")
    public void save(Course course, JcrFile file, InputStream in) {
        jcrFileDao.save(jcrFileDao.getOrCreateFolder(course), file, in);
    }

    @Override
    @Transactional("atom")
    public void delete(Course course, String filename) {
        jcrFileDao.delete(course, filename);
    }

    @Override
    @Transactional("atom")
    public void delete(Course course) {
        jcrFileDao.delete(course);
    }

}
