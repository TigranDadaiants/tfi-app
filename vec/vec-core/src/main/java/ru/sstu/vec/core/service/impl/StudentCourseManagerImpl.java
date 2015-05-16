package ru.sstu.vec.core.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.CourseDao;
import ru.sstu.vec.core.dao.CourseResultDao;
import ru.sstu.vec.core.dao.GroupDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.domain.Group;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.service.StudentCourseManager;

/**
 * {@code StudentCourseManagerImpl} class is {@link StudentCourseManager}
 * implementation.
 *
 * @author Dmitry V. Petrov
 * @author denis_murashev
 * @since VEC 2.0
 */
@Service("studentCourseManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class StudentCourseManagerImpl implements StudentCourseManager {

	private static final long serialVersionUID = -496460374003286621L;

	@Resource
	private GroupDao groupDao;

	@Resource
	private CourseDao courseDao;

	@Resource
	private CourseResultDao courseResultDao;

	private User student;

	@Transactional
	@Override
	public List<CourseResult> find() {
		List<CourseResult> results = courseResultDao.findForStudent(student);
		List<Group> groups = groupDao.findForStudent(student);
		Set<Course> courses = new LinkedHashSet<Course>();
		for (Group g : groups) {
			courses.addAll(g.getCourses());
		}
		for (CourseResult r : results) {
			courses.remove(r.getCourse());
		}
		for (Course c : courses) {
			CourseResult result = new CourseResult();
			result.setStudent(student);
			result.setCourse(c);
			courseResultDao.save(result);
			results.add(result);
		}
		return results;
	}

	@Override
	public void reload(CourseResult object) {
		System.out.println("!! CourseResult: "+object);
	}

	@Override
	public void save(CourseResult object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(CourseResult object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setStudent(User student) {
		this.student = student;
	}
}
