package ru.sstu.vec.core.dao.impl;

import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.LectureDao;
import ru.sstu.vec.core.domain.Lecture;

@Repository("lectureDao")
public class LectureDaoImpl extends GenericDao<Lecture> implements LectureDao {

	private static final long serialVersionUID = 8708249726461674946L;

}
