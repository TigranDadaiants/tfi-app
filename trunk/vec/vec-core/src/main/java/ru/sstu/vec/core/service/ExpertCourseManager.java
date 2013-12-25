package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.User;

/**
 * {@code ExpertCourseManager} interface describes methods for course objects
 * editing management.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public interface ExpertCourseManager extends ItemManager<Course> {

	/**
	 * Sets new current expert.
	 *
	 * @param expert current expert
	 */
	void setExpert(User expert);
}
