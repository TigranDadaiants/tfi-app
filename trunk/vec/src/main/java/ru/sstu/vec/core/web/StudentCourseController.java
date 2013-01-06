package ru.sstu.vec.core.web;

import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.service.StudentCourseManager;

/**
 * {@code StudentCourseController} class is controller for course
 * performing by student.
 *
 * @author Dmitry V. Petrov
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("studentCourseBean")
@SessionScoped
public class StudentCourseController
		extends AbstractItemController<CourseResult> {

	private static final long serialVersionUID = 135082582006048283L;

	private static Logger log = Logger.getLogger(StudentCourseController.class);

	@Resource
	private StudentCourseManager studentCourseManager;

	@Override
	protected StudentCourseManager getManager() {
		studentCourseManager.setStudent(getLoggedUser());
		return studentCourseManager;
	}

	@Override
	protected CourseResult newItem() {
		log.error("Students cannot create courses.");
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getListViewId() {
		return "studentCoursesList";
	}

	@Override
	protected String getItemViewId() {
		return "studentCourseInfo";
	}
}
