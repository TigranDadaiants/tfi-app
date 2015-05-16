package ru.sstu.vec.core.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;

import ru.sstu.dao.AbstractDao;

/**
 * <code>GenericDao</code> class contains generic methods for data access.
 *
 * @author Denis A. Murashev
 *
 * @param <T> concrete entity class
 * @since VEC 1.0
 */
abstract class GenericDao<T extends Serializable> extends AbstractDao<T>
		implements Serializable {

	private static final long serialVersionUID = -340925501063896007L;

	@Resource
	private HibernateTemplate template;

	@Override
	public void save(T entity) {
		template.clear();
		super.save(entity);
		template.flush();
	}

	@Override
	protected HibernateTemplate getTemplate() {
		return template;
	}
}
