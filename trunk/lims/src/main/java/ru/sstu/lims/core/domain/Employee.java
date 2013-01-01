package ru.sstu.lims.core.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * {@code Employee} class represents employee.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
@Entity
@Table(name = "EMPLOYEES")
public class Employee implements Serializable {

	private static final long serialVersionUID = 7793081199874709210L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMPLOYEE_ID_PK", unique = true, nullable = false)
	private long id = -1L;

	@OneToOne(mappedBy = "employee")
	private User user;

	@ManyToOne
	@JoinColumn(name = "POSITION_ID_FK")
	private Position position;

	@Column(name = "FULL_NAME", unique = false, nullable = true)
	private String fullName;

	@Column(name = "SHORT_NAME", unique = false, nullable = true)
	private String shortName;

	@Column(name = "ENGLISH_NAME", unique = false, nullable = true)
	private String englishName;

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE", unique = false, nullable = true)
	private Date birthDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "DISMISS_DATE", unique = false, nullable = true)
	private Date dismissDate;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the englishName
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * @param englishName the englishName to set
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the dismissDate
	 */
	public Date getDismissDate() {
		return dismissDate;
	}

	/**
	 * @param dismissDate the dismissDate to set
	 */
	public void setDismissDate(Date dismissDate) {
		this.dismissDate = dismissDate;
	}

	/**
	 * Calculates employee's age.
	 *
	 * @return age
	 */
	public int getAge() {
		Calendar now = Calendar.getInstance();
		Calendar dob = Calendar.getInstance();
		dob.setTime(birthDate);
		int age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
		dob.add(Calendar.YEAR, age);
		if (!now.after(dob)) {
			age--;
		}
		return age;
	}
}
