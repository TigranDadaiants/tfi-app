package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.Lecture;

/**
 * <code>LectureDao</code> interface is DAO for working with Lecture objects.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.1
 *
 */
public interface LectureDao extends Dao<Lecture> {

    /**
     * Looking for list of lectures for given course.
     *
     * @param course
     *            course
     * @return list of lectures
     */
    List<Lecture> find(Course course);

    /**
     * Looking for lecture with given name.
     *
     * @param course
     *            course
     * @param name
     *            lecture's name
     * @return lecture
     */
    Lecture find(Course course, String name);

    /**
     * Looking for list of lectures for given lab.
     *
     * @param lab
     *            lab
     * @return list of lectures
     */
    List<Lecture> find(Lab lab);

}
