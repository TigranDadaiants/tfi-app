package ru.sstu.lims.core.domain;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * {@code Study} class represent study in LIMS.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
@Entity
@Table(name = "STUDIES")
public class Study implements Serializable {

//	private static final long serialVersionUID = 3684150956106033318L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STUDY_ID_PK", unique = true, nullable = false)
	private long id = -1L;

	@Column(name = "STUDY_NUMBER", unique = true, nullable = false)
	private String number;

	@Column(name = "STUDY_NAME", unique = true, nullable = false)
	private String name;

	@Column(name = "STUDY_OBJECTS", unique = true, nullable = true)
	private String objects;

	@Enumerated(EnumType.STRING)
	@Column(name = "STUDY_IDENTITY", nullable = false)
	private StudyIdentity identity = StudyIdentity.NORMAL;

	@Column(name = "IS_GLP", nullable = false)
	private boolean glp;

	@Enumerated(EnumType.STRING)
	@Column(name = "LEGAL_STATUS", nullable = false)
	private LegalStatus legalStatus = LegalStatus.CONTRACTUAL;

	@ManyToOne
	@JoinColumn(name = "DIRECTOR_ID_FK")
	private Employee director;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_DESIGNATION", nullable = false)
	private Date dateOfDesignation;

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
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
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
	 * @return the objects
	 */
	public String getObjects() {
		return objects;
	}

	/**
	 * @param objects the objects to set
	 */
	public void setObjects(String objects) {
		this.objects = objects;
	}

	/**
	 * @return the identity
	 */
	public StudyIdentity getIdentity() {
		return identity;
	}

	/**
	 * @param identity the identity to set
	 */
	public void setIdentity(StudyIdentity identity) {
		this.identity = identity;
	}

	/**
	 * @return the glp
	 */
	public boolean isGlp() {
		return glp;
	}

	/**
	 * @param glp the glp to set
	 */
	public void setGlp(boolean glp) {
		this.glp = glp;
	}

	/**
	 * @return the legalStatus
	 */
	public LegalStatus getLegalStatus() {
		return legalStatus;
	}

	/**
	 * @param legalStatus the legalStatus to set
	 */
	public void setLegalStatus(LegalStatus legalStatus) {
		this.legalStatus = legalStatus;
	}

	/**
	 * @return the director
	 */
	public Employee getDirector() {
		return director;
	}

	/**
	 * @param director the director to set
	 */
	public void setDirector(Employee director) {
		this.director = director;
	}

	/**
	 * @return the dateOfDesignation
	 */
	public Date getDateOfDesignation() {
		return dateOfDesignation;
	}

	/**
	 * @param dateOfDesignation the dateOfDesignation to set
	 */
	public void setDateOfDesignation(Date dateOfDesignation) {
		this.dateOfDesignation = dateOfDesignation;
	}
}
