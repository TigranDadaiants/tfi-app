package ru.sstu.vec.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ru.sstu.vec.core.dao.CourseResultDao;
import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.service.TeacherCourseManager;

@Service("teacherCourseManager")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class TeacherCourseManagerImpl implements TeacherCourseManager {

	private static final long serialVersionUID = 4862227772392830769L;

	@Resource
	private CourseResultDao courseResultDao;

	private User teacher;

	@Override
	public List<CourseResult> find() {
		return courseResultDao.findForTeacher(teacher);
	}

	@Override
	public void reload(CourseResult object) {
	}

	@Override
	public void save(CourseResult object) {
	}

	@Override
	public void delete(CourseResult object) {
	}

	@Override
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
}
