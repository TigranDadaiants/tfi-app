package ru.sstu.lims.core.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.lims.core.dao.RoleDao;
import ru.sstu.lims.core.domain.Role;

@Repository("roleDao")
class RoleDaoImpl extends GenericDao<Role> implements RoleDao {

	private static final long serialVersionUID = 7136044971140526911L;

	@Override
	public Role find(String name) {
		return unique(getCriteria().add(Restrictions.eq("name", name)));
	}
}
