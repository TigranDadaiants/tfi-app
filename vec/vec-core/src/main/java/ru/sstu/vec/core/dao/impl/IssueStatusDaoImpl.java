package ru.sstu.vec.core.dao.impl;

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
class IssueStatusDaoImpl extends EnumDao<IssueStatus>
		implements IssueStatusDao {

	@Override
	public IssueStatus[] findAll() {
		return IssueStatus.values();
	}
}
