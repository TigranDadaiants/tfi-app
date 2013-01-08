package ru.sstu.lims.core.dao.impl;

import org.springframework.stereotype.Repository;

import ru.sstu.lims.core.dao.StudyDao;
import ru.sstu.lims.core.domain.Study;

@Repository("studyDao")
class StudyDaoImpl extends GenericDao<Study> implements StudyDao {

	private static final long serialVersionUID = -5768375309133674241L;
}
