package ru.sstu.lims.core.service;

import java.io.Serializable;
import java.util.List;

/**
 * {@code ItemService} interface represents common business logic.
 *
 * @author Denis_Murashev
 *
 * @param <T> concrete domain model class
 *
 * @since LIMS 1.0
 */
public interface ItemService<T extends Serializable> {

	/**
	 * @return all items in the data store
	 */
	List<T> getAll();

	/**
	 * @param id item identifier
	 * @return item with given id
	 */
	T getById(long id);

	/**
	 * @param item item to be reloaded
	 */
	void reload(T item);

	/**
	 * @param item item to be saved
	 */
	void save(T item);

	/**
	 * @param item item to be deleted
	 */
	void delete(T item);
}
