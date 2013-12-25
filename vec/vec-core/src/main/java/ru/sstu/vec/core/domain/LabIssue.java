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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * {@code LabIssue} class represents lab issue.
 *
 * @author Denis A. Murashev
 * @author Dmitry V. Petrov
 * @since VEC 1.0
 */
@Entity
@Table(name = "LAB_ISSUES")
public class LabIssue implements Serializable {

	private static final long serialVersionUID = -9016433082002502021L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LAB_ISSUE_ID_PK")
	private long id = -1L;

	@ManyToOne
	@JoinColumn(name = "LAB_ATTEMPT_ID_FK", nullable = false)
	private LabAttempt labAttempt;

	@Lob
	@Column(name = "LAB_ISSUE_DESCRIPTION", nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "LAB_ISSUE_STATUS", nullable = false)
	private IssueStatus status = IssueStatus.OPEN;

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
	 * @return the labAttempt
	 */
	public LabAttempt getLabAttempt() {
		return labAttempt;
	}

	/**
	 * @param labAttempt the labAttempt to set
	 */
	public void setLabAttempt(LabAttempt labAttempt) {
		this.labAttempt = labAttempt;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return bug status
	 */
	public IssueStatus getStatus() {
		return status;
	}

	/**
	 * @param status bug status to set
	 */
	public void setStatus(IssueStatus status) {
		this.status = status;
	}

	/**
	 * Checks if bug is fixed.
	 *
	 * @return true if bug is fixed or closed
	 */
	public boolean isFixed() {
		return status == IssueStatus.FIXED
				|| status == IssueStatus.CLOSED;
	}
}
