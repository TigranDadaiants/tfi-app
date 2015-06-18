package ru.sstu.vec.core.service.impl;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ru.sstu.vec.core.domain.Lecture;
import ru.sstu.vec.core.service.ExpertLectureManager;
import ru.sstu.vec.core.service.StudentLectureManager;

/**
 * {@code ExpertLectureManagerImpl} class is {@link ExpertLectureManager}
 * implementation.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.1
 */
@Service("studentLectureManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class StudentLectureManagerImpl extends ExpertLectureManagerImpl
        implements StudentLectureManager {

    private static final long serialVersionUID = 8057074135719048722L;

    @Override
    public void save(Lecture object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Lecture object) {
        throw new UnsupportedOperationException();
    }

}
