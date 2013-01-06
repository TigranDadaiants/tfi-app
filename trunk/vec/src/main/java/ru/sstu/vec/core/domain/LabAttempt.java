package ru.sstu.vec.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * {@code LabAttempt} class represents single attempt to pass lab.
 *
 * @author Denis A. Murashev
 * @author Dmitry V. Petrov
 * @since VEC 1.2
 */
@Entity
@Table(name = "LAB_ATTEMPTS")
public class LabAttempt implements Serializable {

	/**
	 * Lab grade length.
	 */
	public static final int GRADE = 5;

	private static final long serialVersionUID = -6400701937787374132L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LAB_ATTEMPT_ID_PK")
	private long id = -1L;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "LAB_RESULT_ID_FK")
	private LabResult labResult;

	@Column(name = "LAB_GRADE", nullable = false, length = GRADE)
	private String grade = "";

	@Column(name = "LAB_SENT_DATE", nullable = false)
	private Date dateSent;

	@Column(name = "LAB_CHECKED_DATE", nullable = true)
	private Date dateChecked;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "ATTACHEMENT_ID_FK")
	private Attachment attachement;

	@ManyToOne
	@JoinColumn(name = "TEACHER_ID_FK", nullable = true)
	private User teacher;

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
	 * @return the labResult
	 */
	public LabResult getLabResult() {
		return labResult;
	}

	/**
	 * @param labResult lab result
	 */
	public void setLabResult(LabResult labResult) {
		this.labResult = labResult;
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

	/**
	 * @return date sent
	 */
	public Date getDateSent() {
		return dateSent;
	}

	/**
	 * @param dateSent date sent
	 */
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	/**
	 * @return date checked
	 */
	public Date getDateChecked() {
		return dateChecked;
	}

	/**
	 * @param dateChecked date checked
	 */
	public void setDateChecked(Date dateChecked) {
		this.dateChecked = dateChecked;
	}

	/**
	 * @return the attachement
	 */
	public Attachment getAttachement() {
		return attachement;
	}

	/**
	 * @param attachement the attachement to set
	 */
	public void setAttachement(Attachment attachement) {
		this.attachement = attachement;
	}

	/**
	 * @return the teacher
	 */
	public User getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
}
