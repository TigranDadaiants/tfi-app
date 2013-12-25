package ru.sstu.vec.core.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.UserDao;
import ru.sstu.vec.core.domain.User;

/**
 * {@code UserDaoImpl} class is {@link UserDao} interface implementation.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0.1
 */
@Repository("userDao")
class UserDaoImpl extends GenericDao<User> implements UserDao {

	private static final long serialVersionUID = 8307147267778158553L;

	@Override
	public User find(String login) {
		return unique(getCriteria().add(Restrictions.eq("login", login)));
	}
}
