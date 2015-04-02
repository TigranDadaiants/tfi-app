package ru.sstu.vec.core.dao;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.Theme;

/**
 * <code>ThemeDao</code> interface is DAO for working with Theme objects.
 *
 * @author Denis A. Murashev
 * @author Tigran K. Dadajanc
 * @since VEC 2.0
 */
public interface ThemeDao extends Dao<Theme> {

	/**
	 * Looking for theme for given id.
	 *
	 * @param id themes id
	 * @return theme
	 */
	Theme find(long id);

	/**
	 * Looking for theme for given name.
	 *
	 * @param name theme name
	 * @return theme
	 */
	Theme find(String name);
}
