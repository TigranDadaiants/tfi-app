package ru.sstu.lims.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	@Override
	public void save(User user) {
		// FIXME
		if (user.getPassword() != null && !"".equals(user.getPassword())) {
			String password = encoder.encodePassword(user.getPassword(),
					user.getLogin());
			user.setPassword(password);
		} else if (user.getEmployee().getId() != -1L) {
			user.setPassword(userDao
					.findById(user.getEmployee().getId()).getPassword());
		}
		userDao.save(user);
	}

	@Transactional
	@Override
	public void delete(User user) {
		userDao.delete(user);
	}
}
