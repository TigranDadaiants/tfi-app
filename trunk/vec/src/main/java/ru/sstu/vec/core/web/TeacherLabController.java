package ru.sstu.vec.core.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.LabStatus;
import ru.sstu.vec.core.service.ItemManager;
import ru.sstu.vec.core.service.LabResultManager;

/**
 * {@code TeacherLabController} class is controller for lab checking.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("teacherLabBean")
@Scope("session")
public class TeacherLabController extends LabResultController {

	private static final long serialVersionUID = 6156762672920349276L;

	@Resource
	private LabResultManager teacherLabResultManager;

	@Resource
	private TeacherCourseController teacherCourseBean;

	/**
	 * @return list of possible statuses
	 */
	public List<LabStatus> getStatuses() {
		List<LabStatus> statuses = new ArrayList<LabStatus>();
		LabStatus currentStatus = getItem().getStatus();
		if (currentStatus != LabStatus.NEW
				&& currentStatus != LabStatus.WORKING) {
			statuses.add(LabStatus.REJECTED);
		}
		statuses.add(LabStatus.PASSED);
		return statuses;
	}

	@Override
	protected ItemManager<LabResult> getManager() {
		teacherLabResultManager.setCourseResult(teacherCourseBean.getItem());
		return teacherLabResultManager;
	}

	@Override
	protected String getListViewId() {
		return "teacherCourseInfo";
	}

	@Override
	protected String getItemViewId() {
		return "teacherLabInfo";
	}
}
