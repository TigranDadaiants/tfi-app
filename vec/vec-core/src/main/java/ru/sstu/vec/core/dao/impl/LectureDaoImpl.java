package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.LectureDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.Lecture;

@Repository("lectureDao")
public class LectureDaoImpl extends GenericDao<Lecture> implements LectureDao {

    private static final String LABS = "labs";

    private static final long serialVersionUID = 5458043446013189190L;

    private static final String ALIAS = "l";

    private static final String ALIAS_ID = "l.id";

    private static final String NAME = "name";

    private static final String COURSE = "course";

    @Override
    public List<Lecture> find(Course course) {
        return list(getCriteria().add(Restrictions.eq(COURSE, course)));
    }

    @Override
    public Lecture find(Course course, String name) {
        return unique(getCriteria().add(Restrictions.eq(COURSE, course)).add(
                Restrictions.eq(NAME, name)));
    }

    @Override
    public List<Lecture> find(Lab lab) {
        return list(getCriteria().createAlias(LABS, ALIAS).add(
                Restrictions.eq(ALIAS_ID, lab.getId())));
    }

}
