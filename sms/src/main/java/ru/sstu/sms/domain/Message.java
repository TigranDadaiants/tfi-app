package ru.sstu.sms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <code>Message</code> class holds message information.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
@Entity
@Table(name = "MESSAGES")
public class Message implements Serializable {

	private static final long serialVersionUID = -4948518744992345679L;

	/**
	 * Unique id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MESSAGE_ID_PK")
	private long id = -1L;

	/**
	 * Person.
	 */
	@ManyToOne
	@JoinColumn(name = "PERSON_ID_FK", nullable = false)
	private Person person;

	/**
	 * Message template.
	 */
	@ManyToOne
	@JoinColumn(name = "MESSAGE_TEMPLATE_ID_FK", nullable = false)
	private MessageTemplate template;

	/**
	 * Message status.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "MESSAGE_STATUS")
	private MessageStatus status = MessageStatus.NEW;

	/**
	 * Date sent.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_SENT")
	private Date dateSent;

	/**
	 * {@inheritDoc}
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
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the template
	 */
	public MessageTemplate getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(MessageTemplate template) {
		this.template = template;
	}

	/**
	 * @return the status
	 */
	public MessageStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	/**
	 * @return the dateSent
	 */
	public Date getDateSent() {
		return (Date) dateSent.clone();
	}

	/**
	 * @param dateSent the dateSent to set
	 */
	public void setDateSent(Date dateSent) {
		this.dateSent = (Date) dateSent.clone();
	}
}
