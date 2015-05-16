package ru.sstu.vec.core.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * {@code ThemeController} class is component containing a list of all themes.
 *
 * @author Tigran Dadaiants
 * @since VEC 2.1
 */
@Controller("themeBean")
@Scope("singleton")
public class ThemeController extends VecController {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2697915681929443431L;

    @Value("#{'${primefaces.themes}'.split(',')}")
    private List<String> themes;

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }

}
