package ru.sstu.vec.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.LabDao;
import ru.sstu.vec.core.dao.LabVariantDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.service.ExpertLabManager;

/**
 * {@code ExpertLabManagerImpl} class is {@link ExpertLabManager}
 * implementation.
 *
 * @author Denis_Murashev
 * @author Dmitry V. Petrov
 * @since VEC 2.0
 */
@Service("expertLabManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class ExpertLabManagerImpl implements ExpertLabManager {

    private static final long serialVersionUID = -5417509135722092701L;

    @Resource
    private LabDao labDao;

    @Resource
    private LabVariantDao labVariantDao;

    private Course course;

    @Override
    public List<Lab> find() {
        return labDao.find(course);
    }

    @Override
    public void reload(Lab object) {
    }

    @Transactional
    @Override
    public void save(Lab object) {
        labDao.save(object);
    }

    @Override
    @Transactional
    public void delete(Lab object) {
        labVariantDao.delete(object);
        object.getLectures().clear();
        labDao.delete(object);
    }

    @Override
    public void setCourse(Course course) {
        this.course = course;
    }
}
