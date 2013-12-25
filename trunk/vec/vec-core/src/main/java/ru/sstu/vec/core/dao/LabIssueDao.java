package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.LabAttempt;
import ru.sstu.vec.core.domain.LabIssue;
import ru.sstu.vec.core.domain.LabResult;

/**
 * <code>LabIssueDao</code> interface is DAO for working with issues.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
public interface LabIssueDao extends Dao<LabIssue> {

	/**
	 * @param labResult lab result
	 * @return list of issues for given lab result
	 */
	List<LabIssue> find(LabResult labResult);

	/**
	 * @param labAttempt lab attempt
	 * @return list of issues for given lab attempt
	 */
	List<LabIssue> find(LabAttempt labAttempt);
}
