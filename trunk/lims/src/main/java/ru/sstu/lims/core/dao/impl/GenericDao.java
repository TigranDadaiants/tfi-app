package ru.sstu.lims.core.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;

import ru.sstu.dao.AbstractDao;

/**
 * {@code GenericDao} class contains generic methods for data access.
 *
 * @author Denis A. Murashev
 *
 * @param <T> concrete entity class
 * @since LIMS 1.0
 */
abstract class GenericDao<T extends Serializable> extends AbstractDao<T>
		implements Serializable {

	private static final long serialVersionUID = 3773935230749817201L;

	@Resource
	private HibernateTemplate template;

	@Override
	protected HibernateTemplate getTemplate() {
		return template;
	}
}
