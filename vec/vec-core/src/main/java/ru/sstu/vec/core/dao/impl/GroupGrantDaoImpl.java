package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.GroupGrantDao;
import ru.sstu.vec.core.domain.GroupGrant;
import ru.sstu.vec.core.domain.User;

@Repository("groupGrantDao")
class GroupGrantDaoImpl extends GenericDao<GroupGrant>
		implements GroupGrantDao {

	private static final long serialVersionUID = 6542887126389585277L;

	private static final String USER = "id.user";

	@Override
	public List<GroupGrant> find(User user) {
		return list(getCriteria().add(Restrictions.eq(USER, user)));
	}
}
