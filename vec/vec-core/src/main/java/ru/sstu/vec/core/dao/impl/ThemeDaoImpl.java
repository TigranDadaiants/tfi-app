package ru.sstu.vec.core.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.ThemeDao;
import ru.sstu.vec.core.domain.Theme;

/**
 * {@code ThemeDaoImpl} class is {@link ThemeDao} implementation.
 *
 * @author Denis A. Murashev
 * @author Tigran K. Dadajanc
 * @since VEC 2.0
 */
@Repository("themeDao")
public class ThemeDaoImpl extends GenericDao<Theme> implements ThemeDao {

	private static final long serialVersionUID = -8501429910874595491L;

	@Override
	public Theme find(long id) {
		return unique(getCriteria().add(Restrictions.eq("id", id)));
	}

	@Override
	public Theme find(String name) {
		return unique(getCriteria().add(Restrictions.eq("name", name)));
	}


}
