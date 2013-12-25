package ru.sstu.vec.core.domain;

import java.io.Serializable;

/**
 * <code>CourseStatus</code> enum represents course result status.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
public enum CourseStatus implements Serializable {

	/**
	 * In progress status.
	 */
	IN_PROGRESS(0L, "result.status.progress"),

	/**
	 * Passed status.
	 */
	PASSED(1L, "result.status.passed");

	private long id;
	private String key;

	/**
	 * @param id  id
	 * @param key key
	 */
	private CourseStatus(long id, String key) {
		this.id = id;
		this.key = key;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}
