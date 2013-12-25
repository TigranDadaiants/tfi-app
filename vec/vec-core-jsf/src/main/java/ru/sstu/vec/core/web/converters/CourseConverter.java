package ru.sstu.vec.core.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ru.sstu.vec.core.domain.Course;

/**
 * {@code CourseConverter} class is JSF converter for type {@link Course}.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
public class CourseConverter implements Converter {

	private static final String SEPARATOR = ":";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Course course = new Course();
		int index = value.indexOf(SEPARATOR);
		if (index != -1) {
			course.setId(Long.parseLong(value.substring(0, index)));
			course.setName(value.substring(index + 1));
		}
		return course;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		Course course = (Course) value;
		return course.getId() + SEPARATOR + course.getName();
	}
}
