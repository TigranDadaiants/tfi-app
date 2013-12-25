package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lab;

/**
 * {@code ExpertLabManager} interface describes methods for lab objects
 * editing management.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public interface ExpertLabManager extends ItemManager<Lab> {

	/**
	 * Sets actual course object.
	 *
	 * @param course course
	 */
	void setCourse(Course course);
}
