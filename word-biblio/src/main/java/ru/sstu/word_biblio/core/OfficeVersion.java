package ru.sstu.word_biblio.core;

/**
 * <code>OfficeVersion</code> enumeration contains configuration constants
 * for different versions of MS Office.
 *
 * @author denis_murashev
 */
public enum OfficeVersion {

	/**
	 * Office 2007.
	 */
	OFFICE_2007("office12"),

	/**
	 * Office 2010.
	 */
	OFFICE_2010("office14");

	private final String folderName;

	private OfficeVersion(String folderName) {
		this.folderName = folderName;
	}

	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}
}
