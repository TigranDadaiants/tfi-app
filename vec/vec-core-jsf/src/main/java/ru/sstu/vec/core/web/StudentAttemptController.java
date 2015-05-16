package ru.sstu.vec.core.web;

import javax.annotation.Resource;

import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.Attachment;
import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.LabStatus;
import ru.sstu.vec.core.service.LabAttemptManager;

/**
 * {@code StudentAttemptController} class is controller for lab attempt
 * performing by student.
 *
 * @author Dmitry_Petrov
 * @author denis_murashev
 * @since VEC 2.0
 */
@Controller("studentAttemptBean")
@Scope("session")
public class StudentAttemptController extends LabAttemptController {

	private static final long serialVersionUID = 3246334093219236971L;

	@Resource
	private StudentLabController studentLabBean;

	@Resource
	private LabAttemptManager labAttemptManager;

	/**
	 * Uploads lab result.
	 *
	 * @param event upload event
	 */
	public void upload(FileUploadEvent event) {
		String fileName = event.getFile().getFileName();
		int fileNameLength = fileName.length();
		if (fileNameLength > Attachment.NAME) {
			int remnant = fileNameLength - Attachment.NAME;
			fileName = fileName.substring(remnant);
		}
		Attachment attachement = new Attachment();
		attachement.setName(fileName);
		attachement.setData(event.getFile().getContents());

		if (getLabResult().getStatus() == LabStatus.WAITING
				&& studentLabBean.isExists()) {
			getManager().update(attachement);
		} else {
			getManager().create(attachement);
		}
	}

	@Override
	protected String getListViewId() {
		return "studentLabInfo";
	}

	@Override
	protected LabResult getLabResult() {
		return studentLabBean.getItem();
	}
}
