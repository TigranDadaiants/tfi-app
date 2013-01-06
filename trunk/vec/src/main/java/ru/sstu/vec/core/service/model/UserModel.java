package ru.sstu.vec.core.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.sstu.vec.core.domain.CourseGrant;
import ru.sstu.vec.core.domain.GroupGrant;
import ru.sstu.vec.core.domain.User;

/**
 * {@code UserModel} class is {@link User} extension for users editing.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public class UserModel implements Serializable {

	private static final long serialVersionUID = 4429892319857251678L;

	private final User user;
	private List<GroupGrant> groupGrants = new ArrayList<GroupGrant>();
	private List<CourseGrant> courseGrants = new ArrayList<CourseGrant>();

	/**
	 * Initializes user model.
	 *
	 * @param user user
	 */
	public UserModel(User user) {
		this.user = user;
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the groupGrants
	 */
	public List<GroupGrant> getGroupGrants() {
		return groupGrants;
	}

	/**
	 * @param groupGrants the groupGrants to set
	 */
	public void setGroupGrants(List<GroupGrant> groupGrants) {
		this.groupGrants = groupGrants;
	}

	/**
	 * @return the courseGrants
	 */
	public List<CourseGrant> getCourseGrants() {
		return courseGrants;
	}

	/**
	 * @param courseGrants the courseGrants to set
	 */
	public void setCourseGrants(List<CourseGrant> courseGrants) {
		this.courseGrants = courseGrants;
	}
}
