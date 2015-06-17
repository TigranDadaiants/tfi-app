package ru.sstu.vec.core.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.Course;

@Controller("studentFileBean")
@Scope("session")
public class StudentFileController extends AbstractFileController {

    private static final long serialVersionUID = 6402324739395818632L;

    @Resource
    private StudentCourseController studentCourseBean;

    protected Course getCourse() {
        return studentCourseBean.getItem().getCourse();
    }

}
