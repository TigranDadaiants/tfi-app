package ru.sstu.vec.core.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.Theme;
import ru.sstu.vec.core.service.ThemeManager;

/**
 * {@code UserController} class is controller for user management.
 *
 * @author Denis_Murashev
 * @author Dadajanc_Tigran
 * @since VEC 2.0
 */
@Controller("themeBean")
public class ThemeController extends VecController {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2697915681929443431L;

	public static final String DEFAULT_THEME = "aristo";

	@Resource
	private ThemeManager themeManager;

	private Map<String, Theme> themes = new TreeMap<String, Theme>();

	/**
	 * Loading themes from database
	 */
	@PostConstruct
	public void init() {
		List<Theme> themeslist = themeManager.find();
		if (themeslist.isEmpty()) {
			loadDefaultThemes();
		} else {
			for (Theme theme : themeslist) {
				themes.put(theme.getName(), theme);
			}
		}
	}

	private void loadDefaultThemes() {
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("/primefaces-themes.properties"));
			for (final String key : prop.stringPropertyNames()) {
				String value = prop.getProperty(key);
				Theme theme = new Theme(value);
				themeManager.save(theme);
				themes.put(value, theme);
			}
		} catch (IOException e) {
			e.printStackTrace();
			Theme theme = new Theme();
			theme.setName(DEFAULT_THEME);
			themeManager.save(theme);
			themes.put(DEFAULT_THEME, theme);
		}

	}

	/**
	 * @return themes
	 */
	public Map<String, Theme> getThemes() {
		return themes;
	}

	/**
	 * @param themes
	 *            the themes to set
	 */
	public void setThemes(Map<String, Theme> themes) {
		this.themes = themes;
	}
}
