package ru.sstu.vec.core.domain;

import java.io.Serializable;

/**
 * {@code LabStatus} enumeration represents lab result status.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
public enum LabStatus implements Serializable {

	/**
	 * New status.
	 */
	NEW(0L, "result.status.new"),

	/**
	 * Working status.
	 */
	WORKING(1L, "result.status.working"),

	/**
	 * Waiting status.
	 */
	WAITING(2L, "result.status.waiting"),

	/**
	 * Checking status.
	 */
	CHECKING(3L, "result.status.checking"),

	/**
	 * Rejected status.
	 */
	REJECTED(4L, "result.status.rejected"),

	/**
	 * Fixing status.
	 */
	FIXING(5L, "result.status.fixing"),

	/**
	 * Passed status.
	 */
	PASSED(6L, "result.status.passed");

	private long id;
	private String key;

	/**
	 * @param id  id
	 * @param key key
	 */
	private LabStatus(long id, String key) {
		this.id = id;
		this.key = key;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}
