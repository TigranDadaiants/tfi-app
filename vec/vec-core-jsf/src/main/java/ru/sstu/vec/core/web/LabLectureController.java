package ru.sstu.vec.core.web;

import java.util.List;

import javax.annotation.Resource;

import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.Lecture;
import ru.sstu.vec.core.service.ExpertLectureManager;

@Controller("labLectureBean")
@Scope("session")
public class LabLectureController extends VecController {

    private static final long serialVersionUID = -7489530185363904613L;

    @Resource
    private ExpertLabController expertLabBean;

    @Resource
    private ExpertCourseController expertCourseBean;

    @Resource
    private ExpertLectureManager expertLectureManager;

    public List<Lecture> getAll() {
        return expertLectureManager.find(expertLabBean.getItem());
    }

    public DualListModel<Lecture> getLectures() {
        expertLectureManager.setCourse(expertCourseBean.getItem());
        List<Lecture> list = expertLectureManager.find();
        Lab lab = expertLabBean.getItem();
        List<Lecture> assigned = expertLectureManager.find(lab);
        list.removeAll(assigned);
        return new DualListModel<Lecture>(list, assigned);
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
