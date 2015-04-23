package ru.sstu.vec.core.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * {@code CourseGrant} class holds grants for given {@link Course}. Possible
 * grants are for expert and teacher.
 *
 * @author denis_murashev
 * @since VEC 2.0
 */
@Entity
@Table(name = "COURSE_GRANTS")
public class CourseGrant implements Serializable {

	private static final long serialVersionUID = -8235020267425319764L;

	@EmbeddedId
	private CompositeId id = new CompositeId();

	@Column(name = "IS_EXPERT")
	private boolean expert;

	@Column(name = "IS_TEACHER")
	private boolean teacher;

	/**
	 * @return the user
	 */
	public User getUser() {
		return id.user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		id.user = user;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return id.course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		id.course = course;
	}

	/**
	 * @return the expert
	 */
	public boolean isExpert() {
		return expert;
	}

	/**
	 * @param expert
	 *            the expert to set
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
	 * @param teacher
	 *            the teacher to set
	 */
	public void setTeacher(boolean teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return id.user + " for " + id.course + ": " + (expert ? "expert " : "")
				+ (teacher ? "teacher" : "");
	}

	/**
	 * This is just composite key representation.
	 *
	 * @author Denis_Murashev
	 */
	private static class CompositeId implements Serializable {

		private static final long serialVersionUID = -7092175528467669095L;

		@ManyToOne(cascade = CascadeType.REMOVE)
		@JoinColumn(name = "USER_ID_FK", nullable = false)
		private User user;

		@ManyToOne
		@JoinColumn(name = "COURSE_ID_FK", nullable = false)
		private Course course;
	}
}
