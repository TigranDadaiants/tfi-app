package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.domain.LabResult;

/**
 * {@code LabResultManager} interface describes methods for lab result
 * objects management.
 *
 * @author Denis_Murashev
 * @author Dmitry_Petrov
 * @since VEC 2.0
 */
public interface LabResultManager extends ItemManager<LabResult> {

	/**
	 * Sets current course result.
	 *
	 * @param courseResult course result
	 */
	void setCourseResult(CourseResult courseResult);
}
