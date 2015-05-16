package ru.sstu.vec.core.web.validators;

import javax.annotation.Resource;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.dao.GroupDao;
import ru.sstu.vec.core.domain.Group;
import ru.sstu.web.jsf.StringValidator;

/**
 * <code>GroupValidator</code> class is validator for inputs in group
 * profile.
 *
 * @author Dmitry V. Petrov
 * @since VEC 2.0
 */
@Controller("groupValidator")
public class GroupValidator {

	/**
	 * Group DAO resource.
	 */
	@Resource
	private GroupDao groupDao;

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
		validator.checkLength(1, Group.NAME, "error.length", Group.NAME);
		Group group = (Group) component.getAttributes().get("group");
		Group dbGroup = groupDao.find(name);
		validator.checkExists(group, dbGroup, "error.exists");
	}
}
