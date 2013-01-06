package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.LabIssueDao;
import ru.sstu.vec.core.domain.LabAttempt;
import ru.sstu.vec.core.domain.LabIssue;
import ru.sstu.vec.core.domain.LabResult;

/**
 * <code>LabIssueDaoImpl</code> class.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
@Repository("labIssueDao")
class LabIssueDaoImpl extends GenericDao<LabIssue> implements LabIssueDao {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -8343219616507428374L;

	/**
	 * Lab attempt property name.
	 */
	private static final String LAB_ATTEMPT = "labAttempt";

	/**
	 * {@inheritDoc}
	 */
	public List<LabIssue> find(LabResult labResult) {
		DetachedCriteria attempts = DetachedCriteria.forClass(LabAttempt.class);
		attempts.add(Restrictions.eq("labResult", labResult));
		attempts.setProjection(Projections.id());
		return list(getCriteria().add(Subqueries
				.propertyIn(LAB_ATTEMPT, attempts)));
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LabIssue> find(LabAttempt labAttempt) {
		return list(getCriteria().add(Restrictions.eq(LAB_ATTEMPT,
				labAttempt)));
	}
}
