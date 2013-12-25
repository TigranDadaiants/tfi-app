package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.CourseGrantDao;
import ru.sstu.vec.core.domain.CourseGrant;
import ru.sstu.vec.core.domain.User;

@Repository("courseGrantDao")
class CourseGrantDaoImpl extends GenericDao<CourseGrant>
		implements CourseGrantDao {

	private static final long serialVersionUID = -5634475370337827480L;

	private static final String USER = "id.user";

	@Override
	public List<CourseGrant> find(User user) {
		return list(getCriteria().add(Restrictions.eq(USER, user)));
	}
}
