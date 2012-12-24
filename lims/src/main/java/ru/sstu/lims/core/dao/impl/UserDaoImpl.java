package ru.sstu.lims.core.dao.impl;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ru.sstu.dao.AbstractDao;
import ru.sstu.lims.core.dao.UserDao;
import ru.sstu.lims.core.domain.User;

@Repository("userDao")
class UserDaoImpl extends AbstractDao<User> implements UserDao {

	@Resource
	private HibernateTemplate template;

	@Override
	public User findByLogin(String login) {
		return unique(getCriteria().add(Restrictions.eq("login", login)));
	}

	@Override
	protected HibernateTemplate getTemplate() {
		return template;
	}
}
