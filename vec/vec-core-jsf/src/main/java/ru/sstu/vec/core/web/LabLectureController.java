package ru.sstu.vec.core.web;

import java.util.List;

import javax.annotation.Resource;

import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.Lecture;
import ru.sstu.vec.core.service.LectureManager;

@Controller("labLectureBean")
@Scope("session")
public class LabLectureController extends VecController {

    private static final long serialVersionUID = -7489530185363904613L;

    @Resource
    private ExpertLabController expertLabBean;

    @Resource
    private ExpertCourseController expertCourseBean;

    @Resource
    private LectureManager lectureManager;

    public List<Lecture> getAll() {
        return lectureManager.find(expertLabBean.getItem());
    }

    public DualListModel<Lecture> getLectures() {
        lectureManager.setCourse(expertCourseBean.getItem());
        List<Lecture> list = lectureManager.find();
        list.removeAll(expertLabBean.getLectures());
        return new DualListModel<Lecture>(list, expertLabBean.getLectures());
    }

    public void setLectures(DualListModel<Lecture> lectures) {
        expertLabBean.getItem().setLectures(lectures.getTarget());
    }

    public String save() {
        expertLabBean.save();
        return getListViewId();
    }

    public String edit() {
        return getItemViewId();
    }

    public String cancel() {
        return getListViewId();
    }

    protected String getListViewId() {
        return "expertLabInfo";
    }

    protected String getItemViewId() {
        return "labLectureInfo";
    }

}
