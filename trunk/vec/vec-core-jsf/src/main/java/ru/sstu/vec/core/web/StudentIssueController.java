package ru.sstu.vec.core.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.LabIssue;
import ru.sstu.vec.core.service.LabIssueManager;

/**
 * {@code StudentIssueController} class is controller for lab issue
 * performing.
 *
 * @author Dmitry_Petrov
 * @author denis_murashev
 * @since VEC 2.0
 */
@Controller("studentIssueBean")
@Scope("session")
public class StudentIssueController
		extends AbstractItemController<LabIssue> {

	private static final long serialVersionUID = 3782611575588064549L;

	private static Logger log = Logger
			.getLogger(StudentIssueController.class);

	@Resource
	private LabIssueManager labIssueManager;

	@Resource
	private LabResultController studentLabBean;

	@Override
	protected LabIssue newItem() {
		log.error("Students cannot create lab issues");
		throw new UnsupportedOperationException();
	}

	@Override
	protected LabIssueManager getManager() {
		labIssueManager.setLabResult(studentLabBean.getItem());
		return labIssueManager;
	}

	@Override
	protected String getListViewId() {
		return null;
	}

	@Override
	protected String getItemViewId() {
		return null;
	}
}
