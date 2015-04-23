package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.LectureDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lecture;

@Repository("lectureDao")
public class LectureDaoImpl extends GenericDao<Lecture> implements LectureDao {

    private static final long serialVersionUID = 5458043446013189190L;

    private static final String COURSE = "course";

    @Override
    public List<Lecture> find(Course course) {
        return list(getCriteria().add(Restrictions.eq(COURSE, course)));
    }

    @Override
    public Lecture find(Course course, String name) {
        return unique(getCriteria().add(Restrictions.eq(COURSE, course)).add(Restrictions.eq("name", name)));
    }

}
