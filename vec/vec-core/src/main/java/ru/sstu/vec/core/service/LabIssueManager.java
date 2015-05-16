package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.LabIssue;
import ru.sstu.vec.core.domain.LabResult;

/**
 * <code>LabIssueManager</code> interface describes methods for lab issue
 * objects performing management.
 *
 * @author Dmitry_Petrov
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public interface LabIssueManager extends ItemManager<LabIssue> {

	/**
	 * Sets lab result.
	 *
	 * @param labResult lab result
	 */
	void setLabResult(LabResult labResult);
}
