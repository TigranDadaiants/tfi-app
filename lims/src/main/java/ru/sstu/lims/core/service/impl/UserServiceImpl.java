package ru.sstu.lims.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.sstu.lims.core.dao.UserDao;
import ru.sstu.lims.core.domain.User;
import ru.sstu.lims.core.service.UserService;

@Service("userService")
class UserServiceImpl implements UserService {

	@Resource
	private PasswordEncoder encoder;

	@Resource
	private UserDao userDao;

	@Override
	public List<User> getAll() {
		return userDao.find();
	}

	@Override
	public User getById(long id) {
		return userDao.findById(id);
	}

	@Override
	public void reload(User user) {
	}

	@Override
	public void save(User user) {
		if (user.getPassword() != null && !"".equals(user.getPassword())) {
			String password = encoder.encodePassword(user.getPassword(),
					user.getLogin());
			user.setPassword(password);
		} else if (user.getId() != -1L) {
			user.setPassword(userDao.findById(user.getId()).getPassword());
		}
		userDao.save(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}
}
