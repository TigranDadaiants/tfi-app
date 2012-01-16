package ru.sstu.sms.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;

import ru.sstu.dao.AbstractDao;
import ru.sstu.dao.Dao;

/**
 * <code>GenericDao</code> class contains generic methods for data access.
 *
 * @author Denis A. Murashev
 *
 * @param <T> concrete entity class
 * @since SMS 1.0
 */
abstract class GenericDao<T extends Serializable> extends AbstractDao<T>
		implements Dao<T>, Serializable {

	private static final long serialVersionUID = -3239404829758220559L;

	/**
	 * Hibernate template.
	 */
	@Resource
	private HibernateTemplate template;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected HibernateTemplate getTemplate() {
		return template;
	}
}
