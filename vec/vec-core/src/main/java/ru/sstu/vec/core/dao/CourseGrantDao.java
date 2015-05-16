package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.CourseGrant;
import ru.sstu.vec.core.domain.User;

/**
 * {@code CourseGrant} interface represents persistence management methods
 * for {@link CourseGrant} objects.
 *
 * @author denis_murashev
 * @since VEC 2.0
 */
public interface CourseGrantDao extends Dao<CourseGrant> {

	/**
	 * Provides all {@link CourseGrant} objects for given {@link User}.
	 *
	 * @param user user
	 * @return list of group grants
	 */
	List<CourseGrant> find(User user);
}
