package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.LabAttempt;
import ru.sstu.vec.core.domain.LabResult;

/**
 * <code>LabAttemptDao</code> interface is DAO interface for {@link LabAttempt}
 * objects.
 *
 * @author Denis A. Murashev
 * @author Dmitry V. Petrov
 * @since VEC 1.2
 */
public interface LabAttemptDao extends Dao<LabAttempt> {

	/**
	 * Provides attempts for given lab result.
	 *
	 * @param result lab result
	 * @return attempts
	 */
	List<LabAttempt> find(LabResult result);
}
