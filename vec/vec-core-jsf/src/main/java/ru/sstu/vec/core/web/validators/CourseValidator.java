package ru.sstu.vec.core.web.validators;

import javax.annotation.Resource;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.dao.CourseDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.web.jsf.StringValidator;

/**
 * <code>CourseValidator</code> class is validator for inputs in course
 * profile.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("courseValidator")
public class CourseValidator {

	/**
	 * Course DAO resource.
	 */
	@Resource
	private CourseDao courseDao;

	/**
	 * Validates group name input.
	 *
	 * @param context   current context
	 * @param component group name input
	 * @param value     group name value
	 */
	public void checkName(FacesContext context, UIComponent component,
			Object value) {
		String name = (String) value;
		StringValidator validator = new StringValidator(context,
				(UIInput) component, name);
		validator.checkLength(1, Course.NAME, "error.length", Course.NAME);
		Course course = (Course) component.getAttributes().get("course");
		Course dbCourse = courseDao.find(name);
		validator.checkExists(course, dbCourse, "error.exists");
	}
}
