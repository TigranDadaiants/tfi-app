package ru.sstu.vec.core.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ru.sstu.vec.core.domain.Attachment;
import ru.sstu.vec.core.domain.LabAttempt;
import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.service.LabAttemptManager;

/**
 * {@code LabAttemptController} class is common abstract controller for lab
 * attempt objects.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public abstract class LabAttemptController
		extends AbstractItemController<LabAttempt> {

	private static final long serialVersionUID = -6402248370946484090L;

	private static Logger log = Logger.getLogger(LabAttemptController.class);

	@Resource
	private LabAttemptManager labAttemptManager;

	@Resource
	private StudentLabController studentLabBean;

	/**
	 * Downloads lab result file.
	 *
	 * @return <code>null</code>
	 */
	public String download() {
		Attachment file = getItem().getAttachement();
		HttpServletResponse response = getContext().getResponse();
		try {
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + file.getName());
			OutputStream output = response.getOutputStream();
			output.write(file.getData());
			output.flush();
			output.close();
		} catch (IOException e) {
			// TODO Notify user
			log.error("Cannot download file", e);
		}
		complete();
		return null;
	}

	@Override
	protected LabAttemptManager getManager() {
		labAttemptManager.setLabResult(getLabResult());
		return labAttemptManager;
	}

	@Override
	protected LabAttempt newItem() {
		log.error("New LabAttempt should be created automatically.");
		throw new UnsupportedOperationException();
	}

	@Override
	protected String getItemViewId() {
		return null;
	}

	/**
	 * @return current lab result
	 */
	protected abstract LabResult getLabResult();
}
