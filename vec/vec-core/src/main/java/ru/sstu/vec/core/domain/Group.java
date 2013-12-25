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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * {@code Group} class represents users' group in VEC system.
 *
 * @author Denis A. Murashev
 * @since VEC 1.1
 */
@Entity
@Table(name = "GROUPS")
public class Group implements Serializable {

	/**
	 * Common name length.
	 */
	public static final int NAME = 50;

	private static final long serialVersionUID = 2114530894689308841L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GROUP_ID_PK")
	private long id = -1L;

	@Column(name = "GROUP_NAME", nullable = false, length = NAME)
	private String name = "";

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "GROUP_COURSE",
		joinColumns = {@JoinColumn(name = "GROUP_ID_FK",
			referencedColumnName = "GROUP_ID_PK")},
		inverseJoinColumns = {@JoinColumn(name = "COURSE_ID_FK",
			referencedColumnName = "COURSE_ID_PK")})
	private List<Course> courses = Collections.emptyList();

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
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
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
		if (!(obj instanceof Group)) {
			return false;
		}
		Group other = (Group) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return name;
	}
}
