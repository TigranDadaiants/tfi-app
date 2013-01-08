package ru.sstu.lims.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.lims.core.dao.StudyDao;
import ru.sstu.lims.core.domain.Study;
import ru.sstu.lims.core.service.StudyService;

@Service("studyService")
class StudyServiceImpl implements StudyService {

	private static final long serialVersionUID = 8191092299268859311L;

	@Resource
	private StudyDao studyDao;

	@Override
	public List<Study> getAll() {
		return studyDao.find();
	}

	@Override
	public Study getById(long id) {
		return studyDao.findById(id);
	}

	@Override
	public void reload(Study item) {
	}

	@Transactional
	@Override
	public void save(Study item) {
		studyDao.save(item);
	}

	@Transactional
	@Override
	public void delete(Study item) {
		studyDao.delete(item);
	}
}
