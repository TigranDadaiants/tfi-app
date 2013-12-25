package ru.sstu.vec.core.service.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.LabStatus;

@Service("studentLabResultManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class StudentLabResultManager extends LabResultManagerImpl {

	private static final long serialVersionUID = 142970138557246176L;

	@Transactional
	@Override
	public void reload(LabResult object) {
		LabStatus status = object.getStatus();
		if (status == LabStatus.NEW) {
			object.setStatus(LabStatus.WORKING);
			getLabResultDao().save(object);
		} else if (status == LabStatus.REJECTED) {
			object.setStatus(LabStatus.FIXING);
			getLabResultDao().save(object);
		}
	}
}
