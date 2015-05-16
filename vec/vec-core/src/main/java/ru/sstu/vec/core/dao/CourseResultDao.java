package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.domain.User;

/**
 * <code>CourseResultDao</code> interface is DAO for working with course
 * results.
 *
 * @author Denis A. Murashev
 * @since VEC 2.0
 */
public interface CourseResultDao extends Dao<CourseResult> {

	/**
	 * Provides course results for given student.
	 *
	 * @param student student
	 * @return list of course results
	 */
	List<CourseResult> findForStudent(User student);

	/**
	 * Provides course results for given teacher.
	 *
	 * @param teacher teacher
	 * @return list of course results
	 */
	List<CourseResult> findForTeacher(User teacher);

	/**
	 * Provides course result for given course and student.
	 *
	 * @param user   student
	 * @param course course
	 * @return course result
	 */
	CourseResult find(User user, Course course);
}
