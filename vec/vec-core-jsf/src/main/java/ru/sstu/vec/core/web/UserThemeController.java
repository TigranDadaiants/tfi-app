package ru.sstu.vec.core.web;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.domain.UserTheme;
import ru.sstu.vec.core.service.UserThemeManager;

/**
 * {@code UserThemeController} class is controller for theme of current user.
 *
 * @author Tigran Dadaiants
 * @since VEC 2.
 *
 */
@Controller("userThemeBean")
@Scope("session")
public class UserThemeController extends VecController {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8473455459041730312L;

    @Autowired
    private ThemeController themeController;

    @Resource
    private UserThemeManager userThemeManager;

    @Value("${primefaces.default-theme}")
    private String defaultTheme;

    private String theme;

    private UserTheme userTheme;

    @PostConstruct
    private void init() {
        theme = defaultTheme;
    }

    /**
     * Setting theme for current user
     */
    public void loadTheme() {
        User user = getLoggedUser();
        if (user != null) {
            userTheme = userThemeManager.find(user);
            if (userTheme == null) {
                userTheme = new UserTheme();
                userTheme.setUser(user);
                userTheme.setTheme(defaultTheme);
                userThemeManager.save(userTheme);
            } else {
                theme = userTheme.getTheme();
            }
        }
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * Saves chosen theme for current user.
     */
    public void save() {
        userTheme.setTheme(theme);
        userThemeManager.save(userTheme);
    }
}
