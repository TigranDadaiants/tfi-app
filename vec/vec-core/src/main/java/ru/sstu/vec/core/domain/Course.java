package ru.sstu.vec.core.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * {@code Course} class represents learning course in VEC system.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0
 */
@Entity
@Table(name = "COURSES")
public class Course implements Serializable {

	/**
	 * Course name length.
	 */
	public static final int NAME = 50;

	private static final long serialVersionUID = 3638630210426326525L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COURSE_ID_PK")
	private long id = -1L;

	@Column(name = "COURSE_NAME", nullable = false, unique = true, length = NAME)
	private String name = "";

	@ManyToMany(mappedBy = "courses")
	private List<Group> groups = Collections.emptyList();

	/**
	 * Mapped to avoid referential integrity constraint violation.
	 */
	@OneToMany(mappedBy = "id.course", cascade = CascadeType.REMOVE)
	private List<CourseGrant> courseGrants;

	/**
	 * @return Course's id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            course id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return course name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            course name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the groups
	 */
	public List<Group> getGroups() {
		return groups;
	}

	/**
	 * @param groups
	 *            the groups to set
	 */
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public int hashCode() {
		final int offset = 32;
		return (int) (id ^ (id >>> offset));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Course)) {
			return false;
		}
		Course other = (Course) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return name;
	}
}
