package ru.sstu.sms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <code>Person</code> class holds personal information.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
@Entity
@Table(name = "PERSONS")
public class Person implements Serializable {

	/**
	 * Standard name length.
	 */
	public static final int NAME = 50;

	/**
	 * Standard phone length.
	 */
	public static final int PHONE = 15;

	private static final long serialVersionUID = 7990758797615031095L;

	/**
	 * Unique id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_ID_PK")
	private long id;

	/**
	 * Last name.
	 */
	@Column(name = "LAST_NAME", nullable = false, length = NAME)
	private String lastName;

	/**
	 * First name.
	 */
	@Column(name = "FIRST_NAME", nullable = false, length = NAME)
	private String firstName;

	/**
	 * Middle name.
	 */
	@Column(name = "MIDDLE_NAME", nullable = true, length = NAME)
	private String middleName;

	/**
	 * Phone number.
	 */
	@Column(name = "PHONE", unique = true, nullable = false, length = PHONE)
	private String phone;

	/**
	 * Date of birth.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DOB", nullable = true)
	private Date dob;

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
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return (Date) dob.clone();
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = (Date) dob.clone();
	}
}
