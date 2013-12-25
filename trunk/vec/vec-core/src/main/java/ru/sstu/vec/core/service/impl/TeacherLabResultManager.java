package ru.sstu.vec.core.service.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.LabStatus;

@Service("teacherLabResultManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class TeacherLabResultManager extends LabResultManagerImpl {

	private static final long serialVersionUID = -4212080510188487427L;

	@Transactional
	@Override
	public void reload(LabResult object) {
		LabStatus status = object.getStatus();
		if (status == LabStatus.WAITING) {
			object.setStatus(LabStatus.CHECKING);
			getLabResultDao().save(object);
		}
	}
}
