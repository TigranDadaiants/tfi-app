package ru.sstu.lims.core.dao;

import ru.sstu.dao.Dao;
import ru.sstu.lims.core.domain.User;

public interface UserDao extends Dao<User> {

	/**
	 * Finds {@link User} record with given login.
	 *
	 * @param login login
	 * @return existing {@User} or {@code null}
	 */
	User findByLogin(String login);
}
