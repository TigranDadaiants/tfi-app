package ru.sstu.vec.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.GroupDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Group;
import ru.sstu.vec.core.domain.GroupGrant;
import ru.sstu.vec.core.domain.User;

@Repository("groupDao")
class GroupDaoImpl extends GenericDao<Group> implements GroupDao {

	private static final long serialVersionUID = 4356150258096434863L;

	@Override
	public Group find(String name) {
		return unique(getCriteria().add(Restrictions.eq("name", name)));
	}

	@Override
	public List<Group> find(Course course) {
		return course.getGroups();
	}

	@Override
	public List<Group> findForStudent(User student) {
		DetachedCriteria groups = DetachedCriteria.forClass(GroupGrant.class);
		groups.add(Restrictions.eq("student", Boolean.TRUE));
		groups.add(Restrictions.eq("id.user", student));
		groups.setProjection(Projections.property("id.group"));
		return list(groups);
	}
}
