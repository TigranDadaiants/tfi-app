package ru.sstu.lims.core.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.lims.core.dao.UserDao;
import ru.sstu.web.jsf.JsfController;

/**
 * {@code LoginController} class is Web controller for login action.
 *
 * @author Denis_Murashev
 * @since LIMS 1.0
 */
@Controller("login")
@Scope("request")
public class LoginController extends JsfController {

//	private static final long serialVersionUID = -1630552674473485880L;

	// FIXME
	@Resource
	private UserDao userDao;

	/**
	 * Entered username.
	 */
	private String username;

	/**
	 * Entered password.
	 */
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Authenticates user.
	 *
	 * @return {@code null}
	 * @throws ServletException if some error occurs
	 * @throws IOException      if some error occurs
	 */
	public String login() throws ServletException, IOException {
		forward("/j_spring_security_check");
		complete();
		getContext().getSession().setAttribute("user",
				userDao.findByLogin(username));
		return null;
	}
}
