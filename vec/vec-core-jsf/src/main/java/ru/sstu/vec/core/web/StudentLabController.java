package ru.sstu.vec.core.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.LabStatus;
import ru.sstu.vec.core.domain.Lecture;
import ru.sstu.vec.core.service.LabResultManager;

/**
 * {@code StudentLabController} class is controller for lab result performing by
 * sudent.
 *
 * @author Dmitry V. Petrov
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("studentLabBean")
@Scope("session")
public class StudentLabController extends LabResultController {

    private static final long serialVersionUID = -6590060265917204376L;

    @Resource
    private LabResultManager studentLabResultManager;

    @Resource
    private StudentCourseController studentCourseBean;

    /**
     * Checks if results of this lab is uploadable or not.
     *
     * @return <code>true</code> if results are uploadable
     */
    public boolean isUploadable() {
        LabStatus status = getItem().getStatus();
        return status != LabStatus.CHECKING && status != LabStatus.PASSED;
    }

    @Override
    protected LabResultManager getManager() {
        studentLabResultManager.setCourseResult(studentCourseBean.getItem());
        return studentLabResultManager;
    }

    @Override
    protected String getListViewId() {
        return "studentCourseInfo";
    }

    @Override
    protected String getItemViewId() {
        return "studentLabInfo";
    }

    public List<Lecture> getLectures() {
        return getItem().getLab().getLectures();
    }
}
