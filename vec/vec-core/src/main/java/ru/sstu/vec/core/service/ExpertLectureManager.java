package ru.sstu.vec.core.service;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lecture;

/**
 * {@code ExpertLabManager} interface describes methods for lecture objects
 * editing management.
 *
 * @author Tigran Dadaiants
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

}
