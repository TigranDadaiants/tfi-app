package ru.sstu.lims.core.web;

import java.io.Serializable;
import java.util.List;

import ru.sstu.lims.core.service.ItemService;
import ru.sstu.web.jsf.JsfController;

/**
 * {@code AbstractItemController} class is superclass for controllers
 * which manage editing of list of items.
 *
 * @author Denis_Murashev
 *
 * @param <T> item type
 * @since LIMS 1.0
 */
public abstract class AbstractItemController<T extends Serializable>
		extends JsfController {

	private static final long serialVersionUID = -3724731040783611595L;

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
		return getService().getAll();
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
		getService().reload(item);
		return getItemViewId();
	}

	/**
	 * Saves user.
	 *
	 * @return view id
	 */
	public String save() {
		getService().save(item);
		return getListViewId();
	}

	/**
	 * Deletes item.
	 *
	 * @return view id
	 */
	public String delete() {
		getService().delete(item);
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
	protected abstract ItemService<T> getService();

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
