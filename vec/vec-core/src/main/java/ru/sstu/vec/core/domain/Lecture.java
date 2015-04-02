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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LECTURES")
public class Lecture implements Serializable {

	/**
	 * Lecture name length.
	 */
	public static final int NAME = 200;

	private static final long serialVersionUID = 9090820683526060864L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LECT_ID_PK")
	private long id = -1L;

	@Column(name = "LECT_NAME", nullable = false, length = NAME)
	private String name = "";

	@ManyToOne
	@JoinColumn(name = "COURSE_ID_FK", nullable = false)
	private Course course;

	@Column(name = "LECT_INDEX", nullable = false)
	private int index = -1;

	@Lob
	@Column(name = "LECT_TEXT", nullable = false)
	private String text = "";

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "LECT_LAB",
		joinColumns = { @JoinColumn(name = "LECT_ID_FK",
		referencedColumnName = "LECT_ID_PK") },
		inverseJoinColumns = { @JoinColumn(name = "LAB_ID_FK",
		referencedColumnName = "LAB_ID_PK") })
	private List<Lab> labs = Collections.emptyList();

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return task's index in course
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets task's index in course.
	 *
	 * @param index
	 *            index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Provides lab description text.
	 *
	 * @return lab description text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets lab description text.
	 *
	 * @param text
	 *            text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int offset = 32;
		return (int) (id ^ (id >>> offset));
	}

	/**
	 * {@inheritDoc}
	 */
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
		Lecture other = (Lecture) obj;
		return id == other.id;
	}
}
