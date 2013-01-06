package ru.sstu.vec.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.AttachmentDao;
import ru.sstu.vec.core.dao.LabAttemptDao;
import ru.sstu.vec.core.domain.Attachment;
import ru.sstu.vec.core.domain.LabAttempt;
import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.LabStatus;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.service.LabAttemptManager;

/**
 * {@code LabAttemptManagerImpl} class is {@link LabAttemptManager}
 * implementation.
 *
 * @author Dmitry V. Petrov
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Service("labAttemptManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class LabAttemptManagerImpl implements LabAttemptManager {

	private static final long serialVersionUID = 3914413383264519059L;

	@Resource
	private LabAttemptDao labAttemptDao;

	@Resource
	private AttachmentDao attachmentDao;

	private LabResult labResult;

	@Override
	public List<LabAttempt> find() {
		List<LabAttempt> attempts = labAttemptDao.find(labResult);
		for (LabAttempt attempt : attempts) {
			User user = attempt.getTeacher();
			if (user != null) {
				attempt.setTeacher(user);
			}
		}
		return attempts;
	}

	@Override
	public void reload(LabAttempt object) {
	}

	@Transactional
	@Override
	public void save(LabAttempt object) {
		labAttemptDao.save(object);
	}

	@Override
	public void delete(LabAttempt object) {
	}

	@Transactional
	@Override
	public void create(Attachment attachment) {
		LabAttempt attempt = new LabAttempt();
		attempt.setAttachement(attachment);
		attempt.setDateSent(new Date());
		labResult.setStatus(LabStatus.WAITING);
		attempt.setLabResult(labResult);
		save(attempt);
	}

	@Transactional
	@Override
	public void update(Attachment attachment) {
		LabAttempt attempt = getCurrentAttempt();
		Attachment old = attempt.getAttachement();
		attempt.setAttachement(attachment);
		attempt.setDateSent(new Date());
		labResult.setStatus(LabStatus.WAITING);
		attempt.setLabResult(labResult);
		save(attempt);
		attachmentDao.delete(old);
	}

	@Override
	public LabAttempt getCurrentAttempt() {
		List<LabAttempt> attempts = find();
		int size = attempts.size();
		if (size > 0) {
			return attempts.get(size - 1);
		}
		// TODO Probably throw exception
		return null;
	}

	@Override
	public void setLabResult(LabResult labResult) {
		this.labResult = labResult;
	}
}
