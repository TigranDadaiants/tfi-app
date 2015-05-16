package ru.sstu.vec.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.UserThemeDao;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.domain.UserTheme;
import ru.sstu.vec.core.service.UserThemeManager;

/**
 * {@code UserThemeManagerImpl} class is {@link UserThemeManager}
 * implementation.
 *
 * @author Denis_Murashev
 * @author Tigran_Dadajanc
 * @since VEC 2.0
 */
@Service("userThemeManager")
public class UserThemeManagerImpl implements UserThemeManager {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5571482691550286649L;

	@Resource
	private UserThemeDao userThemeDao;

	@Override
	public List<UserTheme> find() {
		return userThemeDao.find();
	}

	@Override
	public UserTheme find(User user) {
		return userThemeDao.find(user);
	}

	@Override
	public void reload(UserTheme object) {
	}

	@Transactional
	@Override
	public void save(UserTheme object) {
		userThemeDao.save(object);
	}

	@Transactional
	@Override
	public void delete(UserTheme object) {
		userThemeDao.delete(object);
	}
}
