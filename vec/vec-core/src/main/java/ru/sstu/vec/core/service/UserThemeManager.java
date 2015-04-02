package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.domain.UserTheme;

/**
 * {@code UserThemeManager} interface describes methods for user theme objects
 * management.
 *
 * @author Denis_Murashev
 * @author Tigran_Dadajanc
 * @since VEC 2.0
 */
public interface UserThemeManager extends ItemManager<UserTheme> {

	/**
	 * Provides user theme for given user
	 *
	 * @param user user
	 * @return user theme
	 */
	UserTheme find(User user);
}
