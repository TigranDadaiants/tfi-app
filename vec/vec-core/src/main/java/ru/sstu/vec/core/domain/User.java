package ru.sstu.vec.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * {@code User} class represents user account of VEC system.
 *
 * @author Denis A. Murashev
 * @author Maria Brueva
 * @since VEC 1.0
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 2490995456611904641L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID_PK")
	private long id = -1L;

	@Column(name = "NAME", nullable = false, unique = false)
	private String name;

	@Column(name = "LOGIN", nullable = false, unique = true)
	private String login;

	@Column(name = "USER_PASSWORD", nullable = false, unique = false)
	private String password;

	@Column(name = "IS_ADMIN")
	private boolean admin;

	@Column(name = "IS_EXPERT")
	private boolean expert;

	@Column(name = "IS_TEACHER")
	private boolean teacher;

	@Column(name = "IS_OBSERVER")
	private boolean observer;

	@Column(name = "IS_STUDENT")
	private boolean student;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * @return the expert
	 */
	public boolean isExpert() {
		return expert;
	}

	/**
	 * @param expert the expert to set
	 */
	public void setExpert(boolean expert) {
		this.expert = expert;
	}

	/**
	 * @return the teacher
	 */
	public boolean isTeacher() {
		return teacher;
	}

	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(boolean teacher) {
		this.teacher = teacher;
	}

	/**
	 * @return the observer
	 */
	public boolean isObserver() {
		return observer;
	}

	/**
	 * @param observer the observer to set
	 */
	public void setObserver(boolean observer) {
		this.observer = observer;
	}

	/**
	 * @return the student
	 */
	public boolean isStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(boolean student) {
		this.student = student;
	}

	@Override
	public int hashCode() {
		return login.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		return login.equals(other.login);
	}

	@Override
	public String toString() {
		return getName();
	}
}
