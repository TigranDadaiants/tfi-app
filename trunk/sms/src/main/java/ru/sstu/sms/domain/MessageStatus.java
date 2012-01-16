package ru.sstu.sms.domain;

import java.io.Serializable;

/**
 * <code>MessageStatus</code> enum represents possible statuses of message.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
public enum MessageStatus implements Serializable {

	/**
	 * New status.
	 */
	NEW(0L, "status.new"),

	/**
	 * Sent status.
	 */
	SENT(1L, "status.sent");

	/**
	 * Status id.
	 */
	private final long id;

	/**
	 * Status name key.
	 */
	private final String key;

	/**
	 * @param id  id
	 * @param key key
	 */
	private MessageStatus(long id, String key) {
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
