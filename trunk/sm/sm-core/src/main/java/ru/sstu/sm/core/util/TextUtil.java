package ru.sstu.sm.core.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.measure.unit.Unit;

/**
 * <code>TextUtil</code> class provides methods for reading text messages from
 * resources file.
 *
 * @author Denis Murashev
 * @since 2.0
 */
public final class TextUtil {

	/**
	 * Resource bundle.
	 */
	private static ResourceBundle bundle = ResourceBundle.getBundle("strings");

	/**
	 * No instances needed.
	 */
	private TextUtil() {
	}

	/**
	 * Provides text message with given key.
	 *
	 * @param key key
	 * @return text message
	 */
	public static String get(String key) {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Provides localized unit name.
	 *
	 * @param unit unit
	 * @return unit name
	 */
	public static String get(Unit<?> unit) {
		return get("unit." + unit.toString());
	}
}
