package ru.sstu.vec.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.LabVariantDao;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.LabVariant;
import ru.sstu.vec.core.service.ExpertVariantManager;

/**
 * {@code ExpertVariantManagerImpl} class is {@link ExpertVariantManager}
 * implementation.
 *
 * @author Denis_Murashev
 * @author Dmitry V. Petrov
 * @since VEC 2.0
 */
@Service("expertVariantManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class ExpertVariantManagerImpl implements ExpertVariantManager {

	private static final long serialVersionUID = 6068265819723334008L;

	@Resource
	private LabVariantDao labVariantDao;

	private Lab lab;

	@Override
	public List<LabVariant> find() {
		return labVariantDao.find(lab);
	}

	@Override
	public void reload(LabVariant object) {
	}

	@Transactional
	@Override
	public void save(LabVariant object) {
		labVariantDao.save(object);
	}

	@Transactional
	@Override
	public void delete(LabVariant object) {
		labVariantDao.delete(object);
	}

	@Override
	public void setLab(Lab lab) {
		this.lab = lab;
	}
}
