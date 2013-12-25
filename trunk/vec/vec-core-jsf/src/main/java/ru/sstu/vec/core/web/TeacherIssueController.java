package ru.sstu.vec.core.web;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.IssueStatus;
import ru.sstu.vec.core.domain.LabIssue;
import ru.sstu.vec.core.domain.LabStatus;
import ru.sstu.vec.core.service.LabIssueManager;

/**
 * {@code TeacherIssueController} class is controller for lab issue
 * checking.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("teacherIssueBean")
@Scope("session")
public class TeacherIssueController extends AbstractItemController<LabIssue> {

	private static final long serialVersionUID = -2676877215053332683L;

	@Resource
	private LabIssueManager labIssueManager;

	@Resource
	private LabResultController teacherLabBean;

	/**
	 * @return list of issue statuses
	 */
	public List<IssueStatus> getStatuses() {
		return Arrays.asList(IssueStatus.values());
	}

	/**
	 * @return <code>true</code> if new issue can be created
	 */
	public boolean isUpdatable() {
		LabStatus status = teacherLabBean.getItem().getStatus();
		return status == LabStatus.CHECKING
				|| status == LabStatus.REJECTED
				|| status == LabStatus.FIXING;
	}

	@Override
	protected LabIssueManager getManager() {
		labIssueManager.setLabResult(teacherLabBean.getItem());
		return labIssueManager;
	}

	@Override
	protected LabIssue newItem() {
		return new LabIssue();
	}

	@Override
	protected String getListViewId() {
		return "teacherLabInfo";
	}

	@Override
	protected String getItemViewId() {
		return "teacherIssueInfo";
	}
}
