package ru.sstu.vec.core.web;

import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.service.TeacherCourseManager;

/**
 * {@code TeacherCourseController} class is controller for course
 * checking by teacher.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("teacherCourseBean")
@SessionScoped
public class TeacherCourseController
		extends AbstractItemController<CourseResult> {

	private static final long serialVersionUID = 5382801230165921611L;

	private static Logger log = Logger.getLogger(TeacherCourseController.class);

	@Resource
	private TeacherCourseManager teacherCourseManager;

	@Override
	protected TeacherCourseManager getManager() {
		teacherCourseManager.setTeacher(getLoggedUser());
		return teacherCourseManager;
	}

	@Override
	protected CourseResult newItem() {
		log.error("Students cannot create courses.");
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getListViewId() {
		return "teacherCoursesList";
	}

	@Override
	protected String getItemViewId() {
		return "teacherCourseInfo";
	}
}
