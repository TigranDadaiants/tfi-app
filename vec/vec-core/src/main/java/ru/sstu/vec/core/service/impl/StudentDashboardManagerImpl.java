package ru.sstu.vec.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.LabResultDao;
import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.LabStatus;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.service.StudentDashboardManager;

/**
 * {@code StudentDashboardManagerImpl} class is {@link StudentDashboardManager}
 * implementation for student scenarios.
 *
 * @author Denis_Murashev
 * @author Dmitry_Petrov
 * @since VEC 2.0
 */
@Service("studentDashboardManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class StudentDashboardManagerImpl implements StudentDashboardManager {

	private static final long serialVersionUID = -8837940915313084961L;

	@Resource
	private LabResultDao labResultDao;

	private User user;

	@Transactional
	@Override
	public List<LabResult> find() {
		return labResultDao.find(user, LabStatus.REJECTED);
	}

	@Override
	public void reload(LabResult object) {
	}

	@Override
	public void save(LabResult object) {
	}

	@Override
	public void delete(LabResult object) {
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
}
