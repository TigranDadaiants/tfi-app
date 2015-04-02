package ru.sstu.vec.core.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.service.UserManager;

/**
 * {@code LoginController} class is Web controller for login action.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("login")
@Scope("request")
public class LoginController extends VecController {

	private static final long serialVersionUID = 606616802514226716L;

	@Resource
	private UserManager userManager;

	@Autowired
	private UserThemeController userThemeController;

	private String username;
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
		setLoggedUser(userManager.find(username).getUser());
		//userThemeController.loadTheme();
		return null;
	}
}
