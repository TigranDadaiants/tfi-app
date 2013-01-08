package ru.sstu.lims.core.domain;

/**
 * {@code LegalStatus} enumeration contains possible legal status types.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
public enum LegalStatus {

	/**
	 * Contractual status.
	 */
	CONTRACTUAL("study.status.legal.contractual"),

	/**
	 * Initiative status.
	 */
	INITIATIVE("study.status.legal.initiative");

	private final String key;

	private LegalStatus(String key) {
		this.key = key;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}
