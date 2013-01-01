package ru.sstu.lims.core.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.lims.core.dao.UserDao;
import ru.sstu.lims.core.domain.User;

@Repository("userDao")
class UserDaoImpl extends GenericDao<User> implements UserDao {

	private static final long serialVersionUID = 481394741720426265L;

	@Override
	public User findByLogin(String login) {
		return unique(getCriteria().add(Restrictions.eq("login", login)));
	}
}
