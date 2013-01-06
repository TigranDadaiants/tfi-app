package ru.sstu.vec.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.CourseDao;
import ru.sstu.vec.core.dao.LabResultDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.LabStatus;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.service.TeacherDashboardManager;

/**
 * {@code TeacherDashboardManagerImpl} class is {@link TeacherDashboardManager}
 * implementation for teacher scenarios.
 *
 * @author Denis_Murashev
 * @author Dmitry_Petrov
 * @since VEC 2.0
 */
@Service("teacherDashboardManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class TeacherDashboardManagerImpl implements TeacherDashboardManager {

	private static final long serialVersionUID = -518545176854071588L;

	@Resource
	private LabResultDao labResultDao;

	@Resource
	private CourseDao courseDao;

	private User user;

	@Transactional
	@Override
	public List<LabResult> find() {
		List<LabResult> results = new ArrayList<LabResult>();
		List<Course> courses = courseDao.findForTeacher(user);
		for (Course c : courses) {
			results.addAll(labResultDao.find(c, LabStatus.WAITING));
			results.addAll(labResultDao.find(c, LabStatus.CHECKING));
		}
		return results;
	}

	@Override
	public void reload(LabResult object) {
	}

	@Override
	public void save(LabResult object) {
	}

	@Override
	public void delete(LabResult object) {
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
}
