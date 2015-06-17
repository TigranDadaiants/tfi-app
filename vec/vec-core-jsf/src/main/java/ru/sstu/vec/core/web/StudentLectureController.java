package ru.sstu.vec.core.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.Lecture;
import ru.sstu.vec.core.service.ItemManager;
import ru.sstu.vec.core.service.LectureManager;

@Controller("studentLectureBean")
@Scope("session")
public class StudentLectureController extends AbstractItemController<Lecture> {

    private static final long serialVersionUID = -8848354233018551322L;

    @Resource
    private LectureManager lectureManager;

    @Resource
    private StudentCourseController studentCourseBean;

    @Override
    protected ItemManager<Lecture> getManager() {
        lectureManager.setCourse(studentCourseBean.getItem().getCourse());
        return lectureManager;
    }

    @Override
    protected Lecture newItem() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getListViewId() {
        return "studentCourseInfo";
    }

    @Override
    protected String getItemViewId() {
        return "studentLectureInfo";
    }
}
