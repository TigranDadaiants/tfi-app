package ru.sstu.lims.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.lims.core.dao.RoleDao;
import ru.sstu.lims.core.domain.Role;
import ru.sstu.lims.core.service.RoleService;

@Service("roleService")
class RoleServiceImpl implements RoleService {

	private static final long serialVersionUID = -424402269450064721L;

	@Resource
	private RoleDao roleDao;

	@Override
	public List<Role> getAll() {
		return roleDao.find();
	}

	@Override
	public Role getById(long id) {
		return roleDao.findById(id);
	}

	@Override
	public void reload(Role item) {
	}

	@Transactional
	@Override
	public void save(Role item) {
		roleDao.save(item);
	}

	@Transactional
	@Override
	public void delete(Role item) {
		roleDao.delete(item);
	}
}
