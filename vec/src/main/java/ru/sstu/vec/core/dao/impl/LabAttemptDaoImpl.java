package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.LabAttemptDao;
import ru.sstu.vec.core.domain.LabAttempt;
import ru.sstu.vec.core.domain.LabResult;

/**
 * <code>LabAttemptDAOImpl</code> class.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
@Repository("labAttemptDao")
class LabAttemptDaoImpl extends GenericDao<LabAttempt>
		implements LabAttemptDao {

	private static final long serialVersionUID = -4896444146247452077L;

	@Override
	public List<LabAttempt> find(LabResult labResult) {
		return list(getCriteria().add(Restrictions.eq("labResult", labResult)));
	}
}
