package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Group;
import ru.sstu.vec.core.domain.User;

/**
 * {@code CourseDao} interface represents DAO for {@link Course} objects.
 *
 * @author denis_murashev
 * @since VEC 1.0
 */
public interface CourseDao extends Dao<Course> {

	/**
	 * Looks for {@link Course} with given name.
	 *
	 * @param name course name
	 * @return course with given name
	 *         or <code>null</code> if course cannot be found
	 */
	Course find(String name);

	/**
	 * Provides courses for given expert.
	 *
	 * @param expert expert
	 * @return list of courses
	 */
	List<Course> findForExpert(User expert);

	/**
	 * Provides courses for given teacher.
	 *
	 * @param teacher teacher
	 * @return list of courses
	 */
	List<Course> findForTeacher(User teacher);

	/**
	 * Looks for courses granted for given group.
	 *
	 * @param group group
	 * @return list of courses
	 */
	List<Course> find(Group group);

}
