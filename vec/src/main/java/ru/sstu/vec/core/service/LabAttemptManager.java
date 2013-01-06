package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.Attachment;
import ru.sstu.vec.core.domain.LabAttempt;
import ru.sstu.vec.core.domain.LabResult;

/**
 * {@code LabAttemptManager} interface describes methods for lab attempt
 * objects performing and checking management.
 *
 * @author Dmitry_Petrov
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public interface LabAttemptManager extends ItemManager<LabAttempt> {

	/**
	 * Creates new attempt with given attachment.
	 *
	 * @param attachment attachment
	 */
	void create(Attachment attachment);

	/**
	 * Updates last attempt with given attachment.
	 *
	 * @param attachment attachment
	 */
	void update(Attachment attachment);

	/**
	 * Provides current attempt to be saved.
	 *
	 * @return current attempt
	 */
	LabAttempt getCurrentAttempt();

	/**
	 * Sets current lab result.
	 *
	 * @param labResult lab result
	 */
	void setLabResult(LabResult labResult);
}
