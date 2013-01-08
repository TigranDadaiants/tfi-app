package ru.sstu.lims.core.domain;

/**
 * {@code StudyIdentity} enumeration contains possible study identity types.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
public enum StudyIdentity {

	/**
	 * Normal study identity.
	 */
	NORMAL("study.identity.normal"),

	/**
	 * Multi-site study identity.
	 */
	MULTI_SITE("study.identity.multisite");

	private final String key;

	private StudyIdentity(String key) {
		this.key = key;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}
