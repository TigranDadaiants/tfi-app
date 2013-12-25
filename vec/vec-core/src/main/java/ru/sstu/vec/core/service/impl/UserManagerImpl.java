package ru.sstu.vec.core.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.CourseDao;
import ru.sstu.vec.core.dao.CourseGrantDao;
import ru.sstu.vec.core.dao.GroupDao;
import ru.sstu.vec.core.dao.GroupGrantDao;
import ru.sstu.vec.core.dao.UserDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.CourseGrant;
import ru.sstu.vec.core.domain.Group;
import ru.sstu.vec.core.domain.GroupGrant;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.service.UserManager;
import ru.sstu.vec.core.service.model.UserModel;

/**
 * {@code UserManagerImpl} class is {@link UserManager} implementation.
 *
 * @author Denis_Murashev
 * @author Dmitry V. Petrov
 * @since VEC 2.0
 */
@Service("userManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class UserManagerImpl implements UserManager {

	private static final long serialVersionUID = -2085985318841089692L;

	@Resource
	private PasswordEncoder encoder;

	@Resource
	private UserDao userDao;

	@Resource
	private GroupDao groupDao;

	@Resource
	private GroupGrantDao groupGrantDao;

	@Resource
	private CourseDao courseDao;

	@Resource
	private CourseGrantDao courseGrantDao;

	@Override
	public List<UserModel> find() {
		List<UserModel> users = new ArrayList<UserModel>();
		for (User u : userDao.find()) {
			users.add(new UserModel(u));
		}
		return users;
	}

	@Override
	public void reload(UserModel model) {
		Set<Group> groups = new LinkedHashSet<Group>(groupDao.find());
		List<GroupGrant> groupGrants = groupGrantDao.find(model.getUser());
		for (GroupGrant g : groupGrants) {
			groups.remove(g.getGroup());
		}
		for (Group g : groups) {
			GroupGrant grant = new GroupGrant();
			grant.setUser(model.getUser());
			grant.setGroup(g);
			groupGrants.add(grant);
		}
		model.setGroupGrants(groupGrants);

		Set<Course> courses = new LinkedHashSet<Course>(courseDao.find());
		List<CourseGrant> courseGrants = courseGrantDao.find(model.getUser());
		for (CourseGrant g : courseGrants) {
			courses.remove(g.getCourse());
		}
		for (Course c : courses) {
			CourseGrant grant = new CourseGrant();
			grant.setUser(model.getUser());
			grant.setCourse(c);
			courseGrants.add(grant);
		}
		model.setCourseGrants(courseGrants);
	}

	@Transactional
	@Override
	public void save(UserModel model) {
		User user = model.getUser();
		if (user.getPassword() != null && !"".equals(user.getPassword())) {
			user.setPassword(encoder.encodePassword(user.getPassword(),
					user.getLogin()));
		} else if (user.getId() != -1L) {
			user.setPassword(userDao.findById(user.getId()).getPassword());
		}
		userDao.save(user);
		for (GroupGrant g : model.getGroupGrants()) {
			groupGrantDao.save(g);
		}
		for (CourseGrant g : model.getCourseGrants()) {
			courseGrantDao.save(g);
		}
	}

	@Override
	public void delete(UserModel model) {
		groupGrantDao.delete(groupGrantDao.find(model.getUser()));
		courseGrantDao.delete(courseGrantDao.find(model.getUser()));
		userDao.delete(model.getUser());
	}

	@Override
	public UserModel find(String login) {
		return new UserModel(userDao.find(login));
	}

	@Transactional
	@Override
	public void changePassword(User user,
			String oldPassword, String newPassword) {
		String encoded = encoder.encodePassword(oldPassword, user.getLogin());
		if (!encoded.equals(user.getPassword())) {
			throw new RuntimeException("Password is incorrect");
		}
		user.setPassword(encoder.encodePassword(newPassword, user.getLogin()));
		userDao.save(user);
	}
}
