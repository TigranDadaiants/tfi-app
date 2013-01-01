package ru.sstu.lims.core.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ru.sstu.lims.core.domain.Position;

/**
 * {@code PositionConverter} class is JSF converter for type {@link Position}.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
public class PositionConverter implements Converter {

	private static final String SEPARATOR = ":";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Position position = new Position();
		int index = value.indexOf(SEPARATOR);
		if (index != -1) {
			position.setId(Long.parseLong(value.substring(0, index)));
			position.setName(value.substring(index + 1));
		}
		return position;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		Position position = (Position) value;
		return position.getId() + SEPARATOR + position.getName();
	}
}
