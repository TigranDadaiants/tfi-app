package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.CourseDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.CourseGrant;
import ru.sstu.vec.core.domain.Group;
import ru.sstu.vec.core.domain.User;

/**
 * {@code CourseDaoImpl} class is {@link CourseDao} implementation.
 *
 * @author Denis A. Murashev
 * @author Dmitry V. Petrov
 * @since VEC 1.0
 */
@Repository("courseDao")
class CourseDaoImpl extends GenericDao<Course> implements CourseDao {

	private static final long serialVersionUID = 5877124420616047083L;

	private static final String USER = "id.user";

	private static final String COURSE = "id.course";

	@Override
	public Course find(String name) {
		return unique(getCriteria().add(Restrictions.eq("name", name)));
	}

	@Override
	public List<Course> findForExpert(User expert) {
		DetachedCriteria courses = DetachedCriteria.forClass(CourseGrant.class);
		courses.add(Restrictions.eq("expert", Boolean.TRUE));
		courses.add(Restrictions.eq(USER, expert));
		courses.setProjection(Projections.property(COURSE));
		return list(courses);
	}

	@Override
	public List<Course> findForTeacher(User expert) {
		DetachedCriteria courses = DetachedCriteria.forClass(CourseGrant.class);
		courses.add(Restrictions.eq("teacher", Boolean.TRUE));
		courses.add(Restrictions.eq(USER, expert));
		courses.setProjection(Projections.property(COURSE));
		return list(courses);
	}

	@Override
	public List<Course> find(Group group) {
		return group.getCourses();
	}
}
