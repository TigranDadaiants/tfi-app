package ru.sstu.vec.core.domain;

import java.io.Serializable;

/**
 * {@code IssueStatus} class represents lab issue status.
 *
 * @author Denis A. Murashev
 * @author Dmitry V. Petrov
 * @since VEC 1.2
 */
public enum IssueStatus implements Serializable {

	/**
	 * Issue is open.
	 */
	OPEN(0L, "issue.status.open"),

	/**
	 * Issue is fixed.
	 */
	FIXED(1L, "issue.status.fixed"),

	/**
	 * Issue is closed.
	 */
	CLOSED(2L, "issue.status.closed");

	private long id;
	private String key;

	/**
	 * @param id  id
	 * @param key key
	 */
	private IssueStatus(long id, String key) {
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
