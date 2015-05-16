package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.service.model.UserModel;

/**
 * {@code UserManager} interface describes methods for user objects
 * management.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public interface UserManager extends ItemManager<UserModel> {

	/**
	 * Provides user model for given login.
	 *
	 * @param login login
	 * @return user model
	 */
	UserModel find(String login);

	/**
	 * Changes user password.
	 *
	 * @param user        user
	 * @param oldPassword old password
	 * @param newPassword new password
	 */
	void changePassword(User user, String oldPassword, String newPassword);
}
