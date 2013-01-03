package ru.sstu.lims.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * {@code Role} class represents functional roles.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
@Entity
@Table(name = "ROLES")
public class Role implements Serializable {

	private static final long serialVersionUID = 8823908233717828158L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID_PK", unique = true, nullable = false)
	private long id = -1L;

	@Column(name = "NAME", unique = true, nullable = false)
	private String name;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + (int) (id ^ (id >>> Integer.SIZE));
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
		Role other = (Role) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return name;
	}
}
