package ru.sstu.vec.core.web;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.service.ItemManager;
import ru.sstu.vec.core.service.TeacherDashboardManager;

/**
 * {@code TeacherDashboardController} class is controller for teacher
 * dash board.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("teacherDashboardBean")
@Scope("session")
public class TeacherDashboardController
		extends AbstractItemController<LabResult> {

	private static final long serialVersionUID = 5321918318428167965L;

	private static Logger log
			= Logger.getLogger(TeacherDashboardController.class);

	@Resource
	private TeacherDashboardManager teacherDashboardManager;

	@Resource
	private TeacherLabController teacherLabBean;

	@Override
	protected ItemManager<LabResult> getManager() {
		teacherDashboardManager.setUser(getLoggedUser());
		return teacherDashboardManager;
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
		teacherLabBean.setItem(getItem());
		return "teacherLabInfo";
	}
}
