package ru.sstu.lims.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * {@code User} class represent user account in LIMS.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 2903749209314765459L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID_PK", unique = true, nullable = false)
	private long id = -1L;

	@Column(name = "FULL_NAME", unique = false, nullable = false)
	private String fullName;

	@Column(name = "LOGIN", unique = true, nullable = false)
	private String login;

	@Column(name = "PASSWORD", unique = false, nullable = false)
	private String password;

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

	@Override
	public String toString() {
		return fullName;
	}
}
