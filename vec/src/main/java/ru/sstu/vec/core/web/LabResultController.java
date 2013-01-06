package ru.sstu.vec.core.web;

import java.util.EnumMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.LabStatus;

/**
 * {@code LabResultController} class is common controller for lab results.
 *
 * @author Dmitry V. Petrov
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public abstract class LabResultController
		extends AbstractItemController<LabResult> {

	private static final long serialVersionUID = 2008307842339364958L;

	private static Logger log = Logger.getLogger(LabResultController.class);

	private static final String IMAGE_AT_TEACHER_SIDE
			= "../images/glass.png";

	private static final String IMAGE_AT_STUDENT_SIDE
			= "../images/sync.jpg";

	private static final Map<LabStatus, String> IMAGES
			= new EnumMap<LabStatus, String>(LabStatus.class);

	static {
		IMAGES.put(LabStatus.NEW, "../images/newdoc.png");
		IMAGES.put(LabStatus.WORKING, IMAGE_AT_STUDENT_SIDE);
		IMAGES.put(LabStatus.WAITING, IMAGE_AT_TEACHER_SIDE);
		IMAGES.put(LabStatus.CHECKING, IMAGE_AT_TEACHER_SIDE);
		IMAGES.put(LabStatus.REJECTED, "../images/exclamation.png");
		IMAGES.put(LabStatus.FIXING, IMAGE_AT_STUDENT_SIDE);
		IMAGES.put(LabStatus.PASSED, "../images/accepted.png");
	}

	/**
	 * Checks if there are attempts in current context.
	 *
	 * @return <code>true</code> if there are attempts
	 */
	public boolean isExists() {
		LabStatus status = getItem().getStatus();
		return status != LabStatus.NEW && status != LabStatus.WORKING;
	}

	/**
	 * Returns images map.
	 *
	 * @return images map
	 */
	public Map<LabStatus, String> getImages() {
		return IMAGES;
	}

	@Override
	protected LabResult newItem() {
		log.error("LabResults should be created automatically.");
		throw new UnsupportedOperationException();
	}
}
