package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lab;

/**
 * <code>LabDao</code> interface is DAO for working with Lab objects.
 *
 * @author Victor A. Lushchenko
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
public interface LabDao extends Dao<Lab> {

	/**
	 * Looking for list of labs for given course.
	 *
	 * @param course course
	 * @return list of labs
	 */
	List<Lab> find(Course course);

	/**
	 * Looking for lab with given name.
	 *
	 * @param course course
	 * @param name   lab's name
	 * @return lab
	 */
	Lab find(Course course, String name);
}
