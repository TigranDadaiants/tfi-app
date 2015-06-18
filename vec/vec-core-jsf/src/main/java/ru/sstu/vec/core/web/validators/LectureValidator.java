package ru.sstu.vec.core.web.validators;

import javax.annotation.Resource;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.dao.LectureDao;
import ru.sstu.vec.core.domain.Lecture;
import ru.sstu.web.jsf.StringValidator;

@Controller("lectureValidator")
public class LectureValidator {

    @Resource
    private LectureDao lectureDao;

    public void checkName(FacesContext context, UIComponent component,
            Object value) {
        String name = (String) value;
        StringValidator validator = new StringValidator(context,
                (UIInput) component, name);
        validator.checkLength(1, Lecture.NAME, "error.length", Lecture.NAME);
        Lecture lecture = (Lecture) component.getAttributes().get("lecture");
        Lecture dbLab = lectureDao.find(lecture.getCourse(), name);
        validator.checkExists(lecture, dbLab, "error.exists");
    }

}
