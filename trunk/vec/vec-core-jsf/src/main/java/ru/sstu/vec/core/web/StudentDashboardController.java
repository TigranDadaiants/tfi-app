package ru.sstu.vec.core.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.service.ItemManager;
import ru.sstu.vec.core.service.StudentDashboardManager;

/**
 * {@code StudentDashboardController} class is controller for student
 * dash board.
 *
 * @author Denis_Murashev
 * @author Dmitry V. Petrov
 * @since VEC 2.0
 */
@Controller("studentDashboardBean")
@Scope("session")
public class StudentDashboardController
		extends AbstractItemController<LabResult> {

	private static final long serialVersionUID = 7014291000377862755L;

	private static Logger log
			= Logger.getLogger(StudentDashboardController.class);

	@Resource
	private StudentDashboardManager studentDashboardManager;

	@Resource
	private StudentLabController studentLabBean;

	@Override
	protected ItemManager<LabResult> getManager() {
		studentDashboardManager.setUser(getLoggedUser());
		return studentDashboardManager;
	}

	@Override
	protected LabResult newItem() {
		log.error("LabResults should be created automatically.");
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getListViewId() {
		return "dashboard";
	}

	@Override
	protected String getItemViewId() {
		studentLabBean.setItem(getItem());
		return "studentLabInfo";
	}
}
