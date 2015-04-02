package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.Theme;

/**
 * {@code ThemeManager} interface describes methods for theme objects
 * management.
 *
 * @author Denis_Murashev
 * @author Tigran_Dadajanc
 * @since VEC 2.0
 */
public interface ThemeManager extends ItemManager<Theme> {

	/**
	 * Provides theme for given id
	 *
	 * @param id id
	 * @return theme
	 */
	Theme find(long id);

	/**
	 * Provides theme for given name
	 *
	 * @param name name
	 * @return theme
	 */
	Theme find(String name);
}
