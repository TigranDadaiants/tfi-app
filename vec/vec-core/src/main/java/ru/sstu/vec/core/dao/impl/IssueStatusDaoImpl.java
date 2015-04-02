package ru.sstu.vec.core.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import ru.sstu.dao.EnumDao;
import ru.sstu.vec.core.dao.IssueStatusDao;
import ru.sstu.vec.core.domain.IssueStatus;

/**
 * <code>IssueStatusDaoImpl</code> class.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
@Repository("issueStatusDao")
class IssueStatusDaoImpl extends EnumDao<IssueStatus> implements
		IssueStatusDao, Serializable {

	private static final long serialVersionUID = -9055334094009595744L;

	@Override
	public IssueStatus[] findAll() {
		return IssueStatus.values();
	}
}
