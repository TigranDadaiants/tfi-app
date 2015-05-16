package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.LabResultDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.LabStatus;
import ru.sstu.vec.core.domain.User;

/**
 * {@code LabResultDaoImpl} class.
 *
 * @author Denis A. Murashev
 * @author Dmitry V. Petrov
 * @since VEC 1.0
 */
@Repository("labResultDao")
class LabResultDaoImpl extends GenericDao<LabResult> implements LabResultDao {

	private static final long serialVersionUID = 7900616590853432911L;

	private static final String STATUS = "status";

	private static final String COURSE = "course";

	private static final String LAB = "lab";

	private static final String COURSE_RESULT = "courseResult";

	@Override
	public LabResult find(CourseResult courseResult, Lab lab) {
		return unique(getCriteria().add(Restrictions.eq(LAB, lab))
				.add(Restrictions.eq(COURSE_RESULT, courseResult)));
	}

	@Override
	public List<LabResult> find(Course course, LabStatus status) {
		DetachedCriteria labs = DetachedCriteria.forClass(Lab.class);
		labs.add(Restrictions.eq(COURSE, course));
		labs.setProjection(Projections.id());
		return list(getCriteria()
				.add(Subqueries.propertyIn(LAB, labs))
				.add(Restrictions.eq(STATUS, status)));
	}

	@Override
	public List<LabResult> find(User user, LabStatus status) {
		DetachedCriteria results = DetachedCriteria
				.forClass(CourseResult.class);
		results.add(Restrictions.eq("student", user));
		results.setProjection(Projections.id());
		return list(getCriteria()
				.add(Subqueries.propertyIn(COURSE_RESULT, results))
				.add(Restrictions.eq(STATUS, status)));
	}
}
