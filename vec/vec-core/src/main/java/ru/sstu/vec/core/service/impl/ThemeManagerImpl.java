package ru.sstu.vec.core.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import ru.sstu.vec.core.dao.ThemeDao;
import ru.sstu.vec.core.domain.Theme;
import ru.sstu.vec.core.service.ThemeManager;

/**
 * {@code ThemerManagerImpl} class is {@link ThemeManager} implementation.
 *
 * @author Denis_Murashev
 * @author Tigran_Dadajanc
 * @since VEC 2.0
 */
@Service("themeManager")
public class ThemeManagerImpl implements ThemeManager {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6927861200400416713L;
	@Resource
	private ThemeDao themeDao;

	@Override
	public List<Theme> find() {
		return themeDao.find();
	}

	@Override
	public Theme find(long id) {
		return themeDao.find(id);
	}

	@Override
	public void reload(Theme object) {
	}

	@Transactional
	@Override
	public void save(Theme object) {
		themeDao.save(object);
	}

	@Transactional
	@Override
	public void delete(Theme object) {
		themeDao.delete(object);
	}

	@Override
	public Theme find(String name) {
		return themeDao.find(name);
	}
}
