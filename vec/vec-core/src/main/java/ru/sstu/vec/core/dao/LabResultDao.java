package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.LabStatus;
import ru.sstu.vec.core.domain.User;

/**
 * {@code LabResultDao} interface is responsible for working with
 * {@link LabResult} objects.
 *
 * @author Denis A. Murashev
 * @author Dmitry V. Petrov
 * @since VEC 1.0
 */
public interface LabResultDao extends Dao<LabResult> {

	/**
	 * Looking for lab result for given student user and lab.
	 *
	 * @param courseResult course result
	 * @param lab          lab
	 * @return lab result
	 */
	LabResult find(CourseResult courseResult, Lab lab);

	/**
	 * Looking for lab results of given course and status.
	 *
	 * @param course course
	 * @param status status
	 * @return list of lab results
	 */
	List<LabResult> find(Course course, LabStatus status);

	/**
	 * Looking for lab results of given user and status.
	 *
	 * @param user user
	 * @param status status
	 * @return list of lab results
	 */
	List<LabResult> find(User user, LabStatus status);
}
