package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.CourseResultDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.CourseGrant;
import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.domain.User;

/**
 * {@code CourseResultDaoImpl} class.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
@Repository("courseResultDao")
class CourseResultDaoImpl extends GenericDao<CourseResult>
		implements CourseResultDao {

	private static final long serialVersionUID = -3803788905896560472L;

	private static final String STUDENT = "student";
	private static final String COURSE = "course";

	@Override
	public List<CourseResult> findForStudent(User user) {
		return list(getCriteria().add(Restrictions.eq(STUDENT, user)));
	}

	@Override
	public List<CourseResult> findForTeacher(User teacher) {
		DetachedCriteria courses = DetachedCriteria.forClass(CourseGrant.class);
		courses.add(Restrictions.eq("teacher", Boolean.TRUE));
		courses.add(Restrictions.eq("id.user", teacher));
		courses.setProjection(Projections.property("id.course"));
		return list(getCriteria().add(Subqueries.propertyIn(COURSE, courses)));
	}

	@Override
	public CourseResult find(User user, Course course) {
		return unique(getCriteria().add(Restrictions.eq(STUDENT, user))
				.add(Restrictions.eq(COURSE, course)));
	}
}
