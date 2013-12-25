package ru.sstu.vec.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.LabAttemptDao;
import ru.sstu.vec.core.dao.LabIssueDao;
import ru.sstu.vec.core.domain.LabAttempt;
import ru.sstu.vec.core.domain.LabIssue;
import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.service.LabIssueManager;

/**
 * {@code LabIssueManagerImpl} class is {@link LabIssueManager}
 * implementation.
 *
 * @author Dmitry V. Petrov
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Service("labIssueManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class LabIssueManagerImpl implements LabIssueManager {

	private static final long serialVersionUID = -1601689427344822646L;

	@Resource
	private LabIssueDao labIssueDao;

	@Resource
	private LabAttemptDao labAttemptDao;

	private LabResult labResult;

	@Override
	public List<LabIssue> find() {
		return labIssueDao.find(labResult);
	}

	@Override
	public void reload(LabIssue object) {
	}

	@Transactional
	@Override
	public void save(LabIssue object) {
		if (object.getId() == -1L) {
			List<LabAttempt> attempts = labAttemptDao.find(labResult);
			int size = attempts.size();
			object.setLabAttempt(attempts.get(size - 1));
		}
		labIssueDao.save(object);
	}

	@Transactional
	@Override
	public void delete(LabIssue object) {
		labIssueDao.delete(object);
	}

	@Override
	public void setLabResult(LabResult labResult) {
		this.labResult = labResult;
	}
}
