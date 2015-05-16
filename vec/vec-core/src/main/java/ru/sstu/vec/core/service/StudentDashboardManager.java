package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.User;

/**
 * <code>StudentDashboardManager</code> interface contains methods for data
 * management for students dash board.
 *
 * @author Denis_Murashev
 * @author Dmitry_Petrov
 * @since VEC 2.0
 */
public interface StudentDashboardManager extends ItemManager<LabResult> {

	/**
	 * @param user student user
	 */
	void setUser(User user);
}
