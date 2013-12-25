package ru.sstu.vec.core.web;

import java.io.Serializable;
import java.util.List;

import ru.sstu.vec.core.service.ItemManager;

/**
 * <code>AbstractItemController</code> class is superclass for controllers
 * which manage editing of list of items.
 *
 * @author Denis_Murashev
 *
 * @param <T> item type
 * @since VEC 2.0
 */
public abstract class AbstractItemController<T extends Serializable>
		extends VecController {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -7067261556268409126L;

	/**
	 * Current item.
	 */
	private T item;

	/**
	 * @return the item
	 */
	public T getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(T item) {
		this.item = item;
	}

	/**
	 * @return list of all items
	 */
	public List<T> getAll() {
		return getManager().find();
	}

	/**
	 * Adds new user.
	 *
	 * @return view id
	 */
	public String add() {
		item = newItem();
		return getItemViewId();
	}

	/**
	 * Edits existing user.
	 *
	 * @return view id
	 */
	public String edit() {
		getManager().reload(item);
		return getItemViewId();
	}

	/**
	 * Saves user.
	 *
	 * @return view id
	 */
	public String save() {
		getManager().save(item);
		return getListViewId();
	}

	/**
	 * Deletes item.
	 *
	 * @return view id
	 */
	public String delete() {
		getManager().delete(item);
		return null;
	}

	/**
	 * Cancels of item saving.
	 *
	 * @return view id
	 */
	public String cancel() {
		return getListViewId();
	}

	/**
	 * @return {@link ItemManager} implementation for type <code>T</code>.
	 */
	protected abstract ItemManager<T> getManager();

	/**
	 * @return new just created item
	 */
	protected abstract T newItem();

	/**
	 * @return view id for list of items
	 */
	protected abstract String getListViewId();

	/**
	 * @return view id for single item
	 */
	protected abstract String getItemViewId();
}
