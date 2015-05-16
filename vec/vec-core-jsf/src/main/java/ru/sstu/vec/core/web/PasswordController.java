package ru.sstu.vec.core.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.service.UserManager;

/**
 * {@code PasswordController} class controls password changing.
 *
 * @author denis_murashev
 * @since VEC 2.0
 */
@Controller("passwordBean")
@Scope("request")
public class PasswordController extends VecController {

	private static final long serialVersionUID = -9120281655104755743L;

	@Resource
	private UserManager userManager;

	private String oldPassword;
	private String newPassword;
	private String confirmation;

	/**
	 * Saves changed password.
	 *
	 * @return {@code null}
	 */
	public String save() {
		User user = getLoggedUser();
		if (user == null) {
			return null;
		}
		userManager.changePassword(user, oldPassword, newPassword);
		return null;
	}

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the confirmation
	 */
	public String getConfirmation() {
		return confirmation;
	}

	/**
	 * @param confirmation the confirmation to set
	 */
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
}
