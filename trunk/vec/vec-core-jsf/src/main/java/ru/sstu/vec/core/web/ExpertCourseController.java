package ru.sstu.vec.core.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.service.ExpertCourseManager;

/**
 * {@code ExpertCourseController} class is controller for course editing.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("expertCourseBean")
@Scope("session")
public class ExpertCourseController extends AbstractItemController<Course> {

	private static final long serialVersionUID = 5191106643341940466L;

	@Resource
	private ExpertCourseManager expertCourseManager;

	@Override
	protected ExpertCourseManager getManager() {
		expertCourseManager.setExpert(getLoggedUser());
		return expertCourseManager;
	}

	@Override
	protected Course newItem() {
		return new Course();
	}

	@Override
	protected String getListViewId() {
		return "expertCoursesList";
	}

	@Override
	protected String getItemViewId() {
		return "expertCourseInfo";
	}
}
