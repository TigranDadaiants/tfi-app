package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.User;

/**
 * <code>TeacherDashboardManager</code> interface contains methods for data
 * management for teachers dash board.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public interface TeacherDashboardManager extends ItemManager<LabResult> {

	/**
	 * @param user teacher user
	 */
	void setUser(User user);
}
