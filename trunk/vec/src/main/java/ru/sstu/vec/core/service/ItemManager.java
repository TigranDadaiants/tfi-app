package ru.sstu.vec.core.service;

import java.io.Serializable;
import java.util.List;

/**
 * {@code ItemManager} interface contains common object management
 * methods.
 *
 * @author Denis_Murashev
 *
 * @param <T> type
 * @since VEC 2.0
 */
public interface ItemManager<T extends Serializable> extends Serializable {

	/**
	 * @return list of available objects in current context
	 */
	List<T> find();

	/**
	 * Reloads object from data base.
	 *
	 * @param object object
	 */
	void reload(T object);

	/**
	 * Saves object.
	 *
	 * @param object object
	 */
	void save(T object);

	/**
	 * Deletes object.
	 *
	 * @param object object
	 */
	void delete(T object);
}
