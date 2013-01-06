package ru.sstu.vec.core.web;

import java.util.List;

import javax.annotation.Resource;

import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Group;
import ru.sstu.vec.core.service.CourseManager;
import ru.sstu.vec.core.service.GroupManager;

/**
 * {@code GroupController} class is controller for group management.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("groupBean")
@Scope("session")
public class GroupController extends AbstractItemController<Group> {

	private static final long serialVersionUID = -9210468244678291738L;

	@Resource
	private GroupManager groupManager;

	@Resource
	private CourseManager courseManager;

	/**
	 * @return courses model for pick list
	 */
	public DualListModel<Course> getCourses() {
		List<Course> list = courseManager.find();
		list.removeAll(getItem().getCourses());
		return new DualListModel<Course>(list, getItem().getCourses());
	}

	/**
	 * @param courses courses to be assigned for current group
	 */
	public void setCourses(DualListModel<Course> courses) {
		getItem().setCourses(courses.getTarget());
	}

	@Override
	protected GroupManager getManager() {
		return groupManager;
	}

	@Override
	protected Group newItem() {
		return new Group();
	}

	@Override
	protected String getListViewId() {
		return "groupsList";
	}

	@Override
	protected String getItemViewId() {
		return "groupInfo";
	}
}
