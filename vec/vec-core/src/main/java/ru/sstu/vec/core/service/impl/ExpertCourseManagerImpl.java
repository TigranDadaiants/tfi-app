package ru.sstu.vec.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.CourseDao;
import ru.sstu.vec.core.dao.CourseGrantDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.CourseGrant;
import ru.sstu.vec.core.domain.Group;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.service.ExpertCourseManager;

/**
 * {@code ExpertCourseManagerImpl} class is {@link ExpertCourseManager}
 * implementation.
 *
 * @author Denis_Murashev
 * @author Dmitry V. Petrov
 * @since VEC 2.0
 */
@Service("expertCourseManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class ExpertCourseManagerImpl implements ExpertCourseManager {

    private static final long serialVersionUID = 5152214804504759941L;

    @Resource
    private CourseDao courseDao;

    @Resource
    private CourseGrantDao courseGrantDao;

    private User expert;

    @Override
    public List<Course> find() {
        return courseDao.findForExpert(expert);
    }

    @Override
    public void reload(Course object) {
    }

    @Transactional
    @Override
    public void save(Course object) {
        boolean needGrant = object.getId() == -1L;
        courseDao.save(object);
        if (needGrant) {
            CourseGrant grant = new CourseGrant();
            grant.setUser(expert);
            grant.setCourse(object);
            grant.setExpert(true);
            courseGrantDao.save(grant);
        }
    }

    @Override
    @Transactional
    public void delete(Course object) {
        for (Group group : object.getGroups()) {
            group.getCourses().remove(object);
        }
        object.getGroups().clear();
        courseDao.delete(object);
    }

    @Override
    public void setExpert(User expert) {
        this.expert = expert;
    }
}
