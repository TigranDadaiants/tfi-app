package ru.sstu.vec.core.web;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.LabAttempt;
import ru.sstu.vec.core.domain.LabResult;

/**
 * {@code TeacherAttemptController} class is controller for lab attempt
 * checking.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("teacherAttemptBean")
@Scope("session")
public class TeacherAttemptController extends LabAttemptController {

	private static final long serialVersionUID = -2698816687126738657L;

	@Resource
	private LabResultController teacherLabBean;

	/**
	 * Updates lab result status.
	 *
	 * @return <code>null</code>
	 */
	public String update() {
		LabAttempt attempt = getManager().getCurrentAttempt();
		if (attempt != null) {
			attempt.setLabResult(getLabResult());
			attempt.setDateChecked(new Date());
			attempt.setGrade(getLabResult().getGrade());
			attempt.setTeacher(getLoggedUser());
			getManager().save(attempt);
		} else {
			teacherLabBean.save();
		}
		return null;
	}

	@Override
	protected String getListViewId() {
		return "teacherLabInfo";
	}

	@Override
	protected LabResult getLabResult() {
		return teacherLabBean.getItem();
	}
}
