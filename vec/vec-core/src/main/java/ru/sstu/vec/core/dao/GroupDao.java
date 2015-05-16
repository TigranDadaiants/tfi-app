package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Group;
import ru.sstu.vec.core.domain.User;

/**
 * Interface <code>GroupDao</code> is used for storing students groups.
 *
 * @author Denis A. Murashev
 * @since VEC 1.1
 */
public interface GroupDao extends Dao<Group> {

	/**
	 * Finds object with given name.
	 *
	 * @param name object name
	 * @return object with given name
	 * @since 1.2
	 */
	Group find(String name);

	/**
	 * Provides groups with access rights to given course.
	 *
	 * @param course course
	 * @return list of groups
	 */
	List<Group> find(Course course);

	/**
	 * Provides groups for given student.
	 *
	 * @param student student
	 * @return list of groups
	 */
	List<Group> findForStudent(User student);
}
