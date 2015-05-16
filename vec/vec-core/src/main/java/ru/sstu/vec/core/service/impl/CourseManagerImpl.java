package ru.sstu.vec.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.CourseDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.service.CourseManager;

/**
 * {@code CourseManagerImpl} class is {@link CourseManager} implementation.
 *
 * @author Denis_Murashev
 * @author Dmitry V. Petrov
 * @since VEC 2.0
 */
@Service("courseManager")
class CourseManagerImpl implements CourseManager {

	private static final long serialVersionUID = 1359466970023338792L;

	@Resource
	private CourseDao courseDao;

	@Override
	public List<Course> find() {
		return courseDao.find();
	}

	@Override
	public void reload(Course course) {
	}

	@Transactional
	@Override
	public void save(Course course) {
		courseDao.save(course);
	}

	@Transactional
	@Override
	public void delete(Course course) {
		courseDao.delete(course);
	}
}
