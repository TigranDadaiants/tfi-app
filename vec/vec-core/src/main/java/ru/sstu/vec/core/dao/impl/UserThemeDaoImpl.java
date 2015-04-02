package ru.sstu.vec.core.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.UserThemeDao;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.domain.UserTheme;

/**
 * {@code UserThemeDaoImpl} class is {@link UserThemeDao} implementation.
 *
 * @author Denis A. Murashev
 * @author Tigran K. Dadajanc
 * @since VEC 2.0
 */
@Repository("userThemeDao")
public class UserThemeDaoImpl extends GenericDao<UserTheme>
implements UserThemeDao {

	private static final long serialVersionUID = 3029381255892370736L;

	@Override
	public UserTheme find(User user) {
		return unique(getCriteria().add(Restrictions.eq("user", user)));
	}
}
