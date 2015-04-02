package ru.sstu.vec.core.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.Theme;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.domain.UserTheme;
import ru.sstu.vec.core.service.UserThemeManager;

@Controller("userThemeBean")
@Scope("session")
public class UserThemeController extends VecController {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8473455459041730312L;

	@Resource
	private UserThemeManager userThemeManager;

	@Resource
	private ThemeController themeController;

	private String theme = ThemeController.DEFAULT_THEME;

	private UserTheme userTheme;

	/**
	 * Setting theme for current user
	 */
	public void loadTheme() {
		User user = getLoggedUser();

		if (user == null) {
			Map<String, Theme> themes = new HashMap<String, Theme>();
			Theme aristo = new Theme();
			aristo.setName("aristo");
			themes.put("aristo", aristo);
			return;
		}
		userTheme = userThemeManager.find(user);
		Map<String, Theme> themes = themeController.getThemes();
		System.out.println(themes.size());
		Theme themeObj;
		if (userTheme == null) {
			themeObj = themes.values().iterator().next();
			userTheme = new UserTheme();
			userTheme.setUser(user);
			userTheme.setTheme(themeObj);
			userThemeManager.save(userTheme);
			theme = themeObj.getName();
		} else {
			theme = userTheme.getTheme().getName();
		}
	}

	/**
	 * @return current theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme
	 *            is theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * Saves chosen theme for current user
	 */
	public void save() {
		Theme themeObj = themeController.getThemes().get(theme);
		userTheme.setTheme(themeObj);
		userThemeManager.save(userTheme);
	}
}
