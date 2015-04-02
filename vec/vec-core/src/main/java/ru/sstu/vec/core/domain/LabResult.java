package ru.sstu.vec.core.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
 * {@code LabResult} class represents student's result of lab.
 *
 * @author Denis A. Murashev
 * @author Dmitry V. Petrov
 * @since VEC 1.0
 */
@Entity
@Table(name = "LAB_RESULTS")
public class LabResult implements Serializable {

	/**
	 * Length of GRADE field.
	 */
	public static final int GRADE = 5;

	private static final long serialVersionUID = -6147201496495231251L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LAB_RESULT_ID_PK")
	private long id = -1L;

	@ManyToOne
	@JoinColumn(name = "COURSE_RESULT_ID_FK", nullable = false)
	private CourseResult courseResult;

	@ManyToOne
	@JoinColumn(name = "LAB_ID_FK", nullable = false)
	private Lab lab;

	@Enumerated(EnumType.STRING)
	@Column(name = "LAB_RESULT_STATUS")
	private LabStatus status = LabStatus.NEW;

	@Column(name = "LAB_GRADE", nullable = false, length = GRADE)
	private String grade = "";

	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "LAB_VARIANT_ID_FK", nullable = true)
	private LabVariant variant;

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
	 * @return the courseResult
	 * @since VEC 2.0
	 */
	public CourseResult getCourseResult() {
		return courseResult;
	}

	/**
	 * @param courseResult the courseResult to set
	 * @since VEC 2.0
	 */
	public void setCourseResult(CourseResult courseResult) {
		this.courseResult = courseResult;
	}

	/**
	 * @return the lab
	 */
	public Lab getLab() {
		return lab;
	}

	/**
	 * @param lab the lab to set
	 */
	public void setLab(Lab lab) {
		this.lab = lab;
	}

	/**
	 * @return status
	 */
	public LabStatus getStatus() {
		return status;
	}

	/**
	 * Sets status.
	 *
	 * @param status status
	 */
	public void setStatus(LabStatus status) {
		this.status = status;
	}

	/**
	 * @return lab variant
	 */
	public LabVariant getVariant() {
		return variant;
	}

	/**
	 * Sets lab variant.
	 *
	 * @param variant variant
	 */
	public void setVariant(LabVariant variant) {
		this.variant = variant;
	}

	/**
	 * @return grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade grade
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
