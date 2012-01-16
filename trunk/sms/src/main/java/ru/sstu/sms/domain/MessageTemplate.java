package ru.sstu.sms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * <code>MessageTemplate</code> class represents SMS message template.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
@Entity
@Table(name = "MESSAGE_TEMPLATES")
public class MessageTemplate implements Serializable {

	private static final long serialVersionUID = -2698940825530006615L;

	/**
	 * Message template id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MESSAGE_TEMPLATE_ID_PK")
	private long id = -1L;

	/**
	 * Message template text.
	 */
	@Lob
	@Column(name = "TEXT", nullable = false)
	private String text = "";

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
}
