package ru.sstu.vec.core.dao.impl;

import org.springframework.stereotype.Repository;

import ru.sstu.dao.EnumDao;
import ru.sstu.vec.core.dao.ResultStatusDao;
import ru.sstu.vec.core.domain.LabStatus;

/**
 * <code>ResultStatusDaoImpl</code> class is {@link ResultStatusDao}
 * implementation.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
@Repository("resultStatusDao")
class ResultStatusDaoImpl extends EnumDao<LabStatus>
		implements ResultStatusDao {

	@Override
	public LabStatus[] findAll() {
		return LabStatus.values();
	}
}
