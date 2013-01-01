package ru.sstu.lims.core.dao.impl;

import org.springframework.stereotype.Repository;

import ru.sstu.lims.core.dao.PositionDao;
import ru.sstu.lims.core.domain.Position;

@Repository("positionDao")
class PositionDaoImpl extends GenericDao<Position> implements PositionDao {

	private static final long serialVersionUID = 6132099290730322828L;
}
