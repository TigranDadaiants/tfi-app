package ru.sstu.vec.core.web;

import ru.sstu.vec.core.domain.User;
import ru.sstu.web.jsf.JsfController;

/**
 * <code>VecController</code> class is most common controller for VEC project.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public class VecController extends JsfController {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -2859817487247256653L;

	/**
	 * Attribute name for current user.
	 */
	private static final String ATTR_USER = "user";

	/**
	 * @return current logged user
	 */
	protected User getLoggedUser() {
		return getContext().getSessionAttribute(ATTR_USER);
	}

	/**
	 * @param user logged user
	 */
	protected void setLoggedUser(User user) {
		getContext().getSession().setAttribute(ATTR_USER, user);
	}
}
