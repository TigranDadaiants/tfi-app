package ru.sstu.lims.core.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.lims.core.dao.PositionDao;
import ru.sstu.lims.core.domain.Position;

@Repository("positionDao")
class PositionDaoImpl extends GenericDao<Position> implements PositionDao {

	private static final long serialVersionUID = 2237977735277495291L;

	@Override
	public Position find(String name) {
		return unique(getCriteria().add(Restrictions.eq("name", name)));
	}
}
