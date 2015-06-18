package ru.sstu.vec.core.service;

import java.util.List;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.Lecture;

/**
 * {@code ExpertLectureManager} interface describes methods for lecture objects
 * editing management.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.1
 */
public interface ExpertLectureManager extends ItemManager<Lecture> {

    /**
     * Sets actual course object.
     *
     * @param course
     *            course
     */
    void setCourse(Course course);

    /**
     * @param lab
     *            lab object
     * @return list of Lectures for given Lab
     */
    List<Lecture> find(Lab lab);

}
