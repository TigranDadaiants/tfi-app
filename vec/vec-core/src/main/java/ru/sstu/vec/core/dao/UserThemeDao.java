package ru.sstu.vec.core.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.vec.core.domain.User;
import ru.sstu.vec.core.domain.UserTheme;

/**
 * <code>UserThemeDao</code> interface is DAO for working with UserTheme
 * objects.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.1
 */
public interface UserThemeDao extends Dao<UserTheme> {

    /**
     * Looking for all user theme objects
     *
     * @return list of user themes
     *
     */
    List<UserTheme> find();

    /**
     * Looking for user theme for given user.
     *
     * @param user
     *            user
     * @return user theme
     */
    UserTheme find(User user);
}
