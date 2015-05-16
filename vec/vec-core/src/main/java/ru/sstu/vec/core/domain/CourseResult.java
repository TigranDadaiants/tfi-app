package ru.sstu.vec.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * {@code CourseResult} class represents student's result of course.
 *
 * @author Denis A. Murashev
 * @since VEC 2.0
 */
@Entity
@Table(name = "COURSE_RESULTS")
public class CourseResult implements Serializable {

	private static final long serialVersionUID = 6121967398536111635L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COURSE_RESULT_ID_PK")
	private long id = -1L;

	@ManyToOne
	@JoinColumn(name = "STUDENT_ID_FK", nullable = false)
	private User student;

	@ManyToOne
	@JoinColumn(name = "COURSE_ID_FK", nullable = false)
	private Course course;

	@Enumerated(EnumType.STRING)
	@Column(name = "COURSE_RESULT_STATUS")
	private CourseStatus status = CourseStatus.IN_PROGRESS;

	/**
	 * @return course result id
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
	 * @return the student
	 */
	public User getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(User student) {
		this.student = student;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the status
	 */
	public CourseStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(CourseStatus status) {
		this.status = status;
	}
}
