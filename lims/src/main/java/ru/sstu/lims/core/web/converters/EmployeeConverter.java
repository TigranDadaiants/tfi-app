package ru.sstu.lims.core.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ru.sstu.lims.core.domain.Employee;

/**
 * {@code EmployeeConverter} class is JSF converter for type {@link Employee}.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
public class EmployeeConverter implements Converter {

	private static final String SEPARATOR = ":";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Employee employee = new Employee();
		int index = value.indexOf(SEPARATOR);
		if (index != -1) {
			employee.setId(Long.parseLong(value.substring(0, index)));
			employee.setFullName(value.substring(index + 1));
		}
		return employee;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		Employee employee = (Employee) value;
		return employee.getId() + SEPARATOR + employee.getFullName();
	}
}
