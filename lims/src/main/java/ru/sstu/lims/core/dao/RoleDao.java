package ru.sstu.lims.core.dao;

import ru.sstu.dao.Dao;
import ru.sstu.lims.core.domain.Role;

/**
 * {@code RoleDao} interface contains DAO methods for {@link Role} entities.
 *
 * @author Denis_Murashev
 * @since LIMS 1.0
 */
public interface RoleDao extends Dao<Role> {

	/**
	 * Finds {@link Role} with given name.
	 *
	 * @param name role name
	 * @return role
	 */
	Role find(String name);
}
