package ru.sstu.vec.core.dao;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.User;

/**
 * {@code UserDao} interface is DAO for working with {@link User} objects.
 *
 * @author Denis A. Murashev
 * @author Maria Brueva
 * @see User
 * @since VEC 1.0
 */
public interface UserDao extends Dao<User> {

	/**
	 * Looking for user with given login name.
	 *
	 * @param login user's login string
	 * @return found user
	 */
	User find(String login);
}
