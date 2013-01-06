package ru.sstu.vec.core.service.model;

/**
 * <code>LabFileFormat</code> enum contains supported formats for
 * <code>Lab</code>s importing.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public enum LabFileFormat {

	/**
	 * MS Word 2003 XML format.
	 */
	DOC_XML("file.format.doc.xml", "doc,xml,docxml"),

	/**
	 * MS Word 2007 format.
	 */
	DOCX("file.format.docx", "docx");

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
	private LabFileFormat(String key, String types) {
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
