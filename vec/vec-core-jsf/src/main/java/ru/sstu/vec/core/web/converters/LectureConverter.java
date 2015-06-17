package ru.sstu.vec.core.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ru.sstu.vec.core.domain.Lecture;

public class LectureConverter implements Converter {

    private static final String SEPARATOR = ":";

    @Override
    public Object getAsObject(FacesContext context, UIComponent ui, String value) {
        Lecture lecture = new Lecture();
        int index = value.indexOf(SEPARATOR);
        if (index != -1) {
            lecture.setId(Long.parseLong(value.substring(0, index)));
            lecture.setName(value.substring(index + 1));
        }
        return lecture;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent ui, Object value) {
        Lecture lecture = (Lecture) value;
        return lecture.getId() + SEPARATOR + lecture.getName();
    }

}
