package ru.sstu.vec.core.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.CourseGrant;
import ru.sstu.vec.core.domain.GroupGrant;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.service.UserManager;
import ru.sstu.vec.core.service.model.UserModel;

/**
 * {@code UserController} class is controller for user management.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("userBean")
@Scope("session")
public class UserController extends AbstractItemController<UserModel> {

	private static final long serialVersionUID = -4819388044419023937L;

	@Resource
	private UserManager userManager;

	/**
	 * @return group grants
	 */
	public List<GroupGrant> getGroups() {
		return getItem().getGroupGrants();
	}

	/**
	 * @return courses
	 */
	public List<CourseGrant> getCourses() {
		return getItem().getCourseGrants();
	}

	@Override
	protected UserManager getManager() {
		return userManager;
	}

	@Override
	protected UserModel newItem() {
		return new UserModel(new User());
	}

	@Override
	protected String getListViewId() {
		return "usersList";
	}

	@Override
	protected String getItemViewId() {
		return "userInfo";
	}
}
