package ru.sstu.vec.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.CourseDao;
import ru.sstu.vec.core.dao.GroupDao;
import ru.sstu.vec.core.domain.Group;
import ru.sstu.vec.core.service.GroupManager;

/**
 * {@code GroupManagerImpl} class is {@link GroupManager} implementation.
 *
 * @author Denis_Murashev
 * @author Dmitry V. Petrov
 * @since VEC 2.0
 */
@Service("groupManager")
class GroupManagerImpl implements GroupManager {

	private static final long serialVersionUID = 8042157927001696528L;

	@Resource
	private GroupDao groupDao;

	@Resource
	private CourseDao courseDao;

	@Override
	public List<Group> find() {
		return groupDao.find();
	}

	@Override
	public void reload(Group group) {
	}

	@Transactional
	@Override
	public void save(Group group) {
		groupDao.save(group);
	}

	@Transactional
	@Override
	public void delete(Group group) {
		groupDao.delete(group);
	}
}
