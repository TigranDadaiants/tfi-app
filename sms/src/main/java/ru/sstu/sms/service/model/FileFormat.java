package ru.sstu.sms.service.model;

/**
 * <code>LabFileFormat</code> enum contains supported formats for
 * Persons importing.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
public enum FileFormat {

	/**
	 * MS Excel 2003 format.
	 */
	XLS("file.format.xls", "xls"),

	/**
	 * MS Excel 2007 format.
	 */
	XLSX("file.format.xlsx", "xlsx"),

	/**
	 * MS Excel 2007 format.
	 */
	CSV("file.format.csv", "csv");

	/**
	 * Key for description.
	 */
	private final String key;

	/**
	 * Accepted types.
	 */
	private final String types;

	/**
	 * @param key   key
	 * @param types accepted types
	 */
	private FileFormat(String key, String types) {
		this.key = key;
		this.types = types;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the types
	 */
	public String getTypes() {
		return types;
	}
}
