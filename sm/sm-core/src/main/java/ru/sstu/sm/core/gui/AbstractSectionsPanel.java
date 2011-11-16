package ru.sstu.sm.core.gui;

import ru.sstu.sm.core.domain.Config;
import ru.sstu.sm.core.domain.Section;
import ru.sstu.sm.core.gui.dialogs.DialogHelper;
import ru.sstu.sm.core.gui.forms.AbstractForm;
import ru.sstu.sm.core.service.AbstractEngine;

/**
 * <code>AbstractConfigPanel</code> class is {@link AbstractListPanel}
 * implementation for configuration.
 *
 * @author Denis A. Murashev
 * @param <T> type of section
 * @since SM 2.0
 */
public abstract class AbstractSectionsPanel<T extends Section>
		extends AbstractListPanel {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -7451146868404694938L;

	/**
	 * SM engine.
	 */
	private AbstractEngine<T> engine;

	/**
	 * @param title  title
	 * @param engine engine
	 */
	protected AbstractSectionsPanel(String title, AbstractEngine<T> engine) {
		super(title);
		this.engine = engine;
	}

	/**
	 * Provides list of values.
	 *
	 * @return list of values
	 */
	protected Config<?> getValues() {
		return engine.getConfig();
	}

	/**
	 * Handler for adding new value.
	 */
	@SuppressWarnings("unchecked")
	protected void addValue() {
		Section section = DialogHelper.executeDialog(this,
				getSectionForm(null));
		if (section != null) {
			engine.addSection((T) section);
		}
	}

	/**
	 * Handler for editing existing value.
	 *
	 * @param index index of value to be edited
	 */
	@SuppressWarnings("unchecked")
	protected void updateValue(int index) {
		Section section = DialogHelper.executeDialog(this,
				getSectionForm((T) engine.getConfig().get(index)));
		if (section != null) {
			engine.setSection(index, (T) section);
		}
	}

	/**
	 * Handler for deleting value.
	 *
	 * @param index index of value to be deleted
	 */
	protected void deleteValue(int index) {
		engine.deleteSection(index);
	}

	/**
	 * Can the value with given index be edited.
	 *
	 * @param index index
	 * @return true if value can be edited
	 */
	protected boolean canBeEdited(int index) {
		return true;
	}

	/**
	 * Provides form for section properties editing.
	 *
	 * @param section old section
	 * @return form for section editing
	 */
	protected abstract AbstractForm getSectionForm(T section);
}
