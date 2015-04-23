package ru.sstu.vec.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ru.sstu.vec.core.dao.LectureDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lecture;
import ru.sstu.vec.core.service.ExpertLectureManager;

/**
 * {@code ExpertLectureManagerImpl} class is {@link ExpertLectureManager}
 * implementation.
 *
 * @author Tigran Dadaiants
 * @since VEC 2.1
 */
@Service("expertLectureManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ExpertLectureManagerImpl implements ExpertLectureManager {

    private static final long serialVersionUID = -2778533219021619193L;

    @Resource
    private LectureDao lectureDao;

    private Course course;

    @Override
    public List<Lecture> find() {
        return lectureDao.find(course);
    }

    @Override
    public void reload(Lecture object) {
    }

    @Override
    public void save(Lecture object) {
        lectureDao.save(object);
    }

    @Override
    public void delete(Lecture object) {
        lectureDao.delete(object);
    }

    @Override
    public void setCourse(Course course) {
        this.course = course;
    }

}
