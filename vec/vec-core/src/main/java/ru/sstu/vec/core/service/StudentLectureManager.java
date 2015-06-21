package ru.sstu.vec.core.service;

import java.util.List;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.Lecture;

/**
 * {@code StudentLecturebManager} interface describes methods for lecture
 * objects editing management.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.1
 */
public interface StudentLectureManager extends ItemManager<Lecture> {

    void setCourse(Course course);

    List<Lecture> find(Lab lab);

}
