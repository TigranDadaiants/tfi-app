package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.GroupGrant;
import ru.sstu.vec.core.domain.User;

/**
 * {@code GroupGrantDao} interface represents persistence management methods
 * for {@link GroupGrant} objects.
 *
 * @author denis_murashev
 * @since VEC 2.0
 */
public interface GroupGrantDao extends Dao<GroupGrant> {

	/**
	 * Provides all {@link GroupGrant} objects for given {@link User}.
	 *
	 * @param user user
	 * @return list of group grants
	 */
	List<GroupGrant> find(User user);
}
