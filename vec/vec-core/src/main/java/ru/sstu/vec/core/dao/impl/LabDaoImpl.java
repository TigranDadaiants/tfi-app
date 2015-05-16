package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.LabDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lab;

/**
 * {@code LabDaoImpl} class is {@link LabDao} implementation.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
@Repository("labDao")
class LabDaoImpl extends GenericDao<Lab> implements LabDao {

	private static final long serialVersionUID = 4101041564560862610L;

	private static final String COURSE = "course";

	@Override
	public List<Lab> find(Course course) {
		return list(getCriteria().add(Restrictions.eq(COURSE, course)));
	}

	@Override
	public Lab find(Course course, String name) {
		return unique(getCriteria()
				.add(Restrictions.eq(COURSE, course))
				.add(Restrictions.eq("name", name)));
	}
}
