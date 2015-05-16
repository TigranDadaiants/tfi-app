package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.domain.User;

/**
 * {@code TeacherCourseManager} interface describes methods for course
 * objects checking management.
 *
 * @author Dmitry_Petrov
 * @author denis_murashev
 * @since VEC 2.0
 */
public interface TeacherCourseManager extends ItemManager<CourseResult> {

	/**
	 * Sets teacher.
	 *
	 * @param teacher teacher
	 */
	void setTeacher(User teacher);
}
