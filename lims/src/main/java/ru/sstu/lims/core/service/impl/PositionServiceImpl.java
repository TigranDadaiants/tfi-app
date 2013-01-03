package ru.sstu.lims.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.lims.core.dao.PositionDao;
import ru.sstu.lims.core.domain.Position;
import ru.sstu.lims.core.service.PositionService;

@Service("positionService")
class PositionServiceImpl implements PositionService {

	@Resource
	private PositionDao positionDao;

	@Override
	public List<Position> getAll() {
		return positionDao.find();
	}

	@Override
	public Position getById(long id) {
		return positionDao.findById(id);
	}

	@Override
	public void reload(Position item) {
	}

	@Transactional
	@Override
	public void save(Position item) {
		positionDao.save(item);
	}

	@Transactional
	@Override
	public void delete(Position item) {
		positionDao.delete(item);
	}
}
