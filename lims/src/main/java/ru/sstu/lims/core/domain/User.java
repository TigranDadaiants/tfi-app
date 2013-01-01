package ru.sstu.lims.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * {@code User} class represent user account in LIMS.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

//	private static final long serialVersionUID = -8434497639819704089L;

	@Id
	@OneToOne
	@JoinColumn(name = "EMPLOYEE_ID_FK")
	private Employee employee;

	@Column(name = "LOGIN", unique = true, nullable = false)
	private String login;

	@Column(name = "PASSWORD", unique = false, nullable = false)
	private String password;

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
	public int hashCode() {
		final int prime = 31;
		return prime + employee.hashCode();
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
		return employee.equals(other.employee);
	}

	@Override
	public String toString() {
		return employee.toString();
	}
}
