package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.domain.User;

/**
 * {@code StudentCourseManager} interface describes methods for course
 * objects performing management.
 *
 * @author Dmitry_Petrov
 * @author denis_murashev
 * @since VEC 2.0
 */
public interface StudentCourseManager extends ItemManager<CourseResult> {

	/**
	 * Sets student.
	 *
	 * @param student student
	 */
	void setStudent(User student);
}
