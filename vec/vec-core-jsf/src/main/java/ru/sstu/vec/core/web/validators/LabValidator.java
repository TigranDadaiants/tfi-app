package ru.sstu.vec.core.web.validators;

import javax.annotation.Resource;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.dao.LabDao;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.web.jsf.StringValidator;

/**
 * <code>LabValidator</code> class is validator for inputs in lab profile.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("labValidator")
public class LabValidator {

	/**
	 * Lab DAO resource.
	 */
	@Resource
	private LabDao labDao;

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
		validator.checkLength(1, Lab.NAME_LENGTH, "error.length", Lab.NAME_LENGTH);
		Lab lab = (Lab) component.getAttributes().get("lab");
		Lab dbLab = labDao.find(lab.getCourse(), name);
		validator.checkExists(lab, dbLab, "error.exists");
	}
}
