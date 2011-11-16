package ru.sstu.sm.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.measure.converter.UnitConverter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * <code>Config</code> class is abstract class for any task configuration.
 * Usually configuration consists of sections with some properties.
 *
 * @author Denis_Murashev
 * @param <T> type of section
 * @since SM 1.0
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Config<T extends Section> implements Iterable<T>,
		Serializable {

	/**
	 * Actual configuration version.
	 */
	public static final String VERSION = "3.0";

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -6933498973715989349L;

	/**
	 * Direction.
	 */
	@XmlElement
	private Direction direction = Direction.UP;

	/**
	 * Type.
	 */
	@XmlElement
	private Type type = Type.FREE;

	/**
	 * Sections.
	 */
	private final List<T> sections = new ArrayList<T>();

	/**
	 * Factors.
	 * @since SM 3.0
	 */
	private final Map<Integer, UnitConverter> converters
			= new HashMap<Integer, UnitConverter>();

	/**
	 * Provides plot's direction.
	 *
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Sets plot's direction.
	 *
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Provides task type.
	 *
	 * @return type of the task
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Sets task type.
	 *
	 * @param type type
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * {@inheritDoc}
	 */
	public Iterator<T> iterator() {
		return sections.iterator();
	}

	/**
	 * @return sections count
	 */
	public int size() {
		return sections.size();
	}

	/**
	 * @param index section index
	 * @return section
	 */
	public T get(int index) {
		return sections.get(index);
	}

	/**
	 * Adds section to configuration.
	 *
	 * @param section section
	 */
	public void add(T section) {
		sections.add(section);
	}

	/**
	 * Adds given sections.
	 *
	 * @param data sections
	 */
	public void addAll(T... data) {
		sections.addAll(Arrays.asList(data));
	}

	/**
	 * Changes section with given index.
	 *
	 * @param index   index
	 * @param section section
	 */
	public void set(int index, T section) {
		sections.set(index, section);
	}

	/**
	 * Removes section with given index.
	 *
	 * @param index index
	 */
	public void remove(int index) {
		sections.remove(index);
	}

	/**
	 * Removes all sections.
	 */
	public void clear() {
		sections.clear();
	}

	/**
	 * Provides converter for given type.
	 *
	 * @param type type
	 * @return converter
	 * @since SM 3.0
	 */
	public UnitConverter getConverter(int type) {
		return converters.get(type);
	}

	/**
	 * Sets converter for given type.
	 *
	 * @param type      type
	 * @param converter converter
	 * @since SM 3.0
	 */
	public void setConverter(int type, UnitConverter converter) {
		converters.put(type, converter);
	}

	/**
	 * Task type.
	 */
	public enum Type {

		/**
		 * Free type for statically solvable tasks.
		 */
		FREE,

		/**
		 * Hard type, for statically unsolvable tasks.
		 */
		HARD
	}

	/**
	 * @return version
	 */
	@XmlAttribute
	protected String getVersion() {
		return VERSION;
	}

	/**
	 * @return supported module name
	 */
	@XmlAttribute
	protected abstract String getModule();

	/**
	 * This method should be annotated in subclasses for using JAXB.
	 *
	 * @return sections
	 */
	protected List<T> getSections() {
		return sections;
	}
}
