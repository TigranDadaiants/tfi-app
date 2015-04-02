package ru.sstu.vec.core.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * {@code LabVariant} class represents lab variant.
 *
 * @author Denis A. Murashev
 * @since VEC 1.0.1
 */
@Entity
@Table(name = "LAB_VARIANTS")
public class LabVariant implements Serializable {

	private static final long serialVersionUID = 1520244760535808461L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LAB_VARIANT_ID_PK")
	private long id = -1L;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "LAB_ID_FK")
	private Lab lab;

	@Column(name = "LAB_VARIANT_INDEX", nullable = false)
	private int index = -1;

	@Lob
	@Column(name = "LAB_VARIANT_TEXT", nullable = false)
	private String text = "";

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
	 * @return parent lab
	 */
	public Lab getLab() {
		return lab;
	}

	/**
	 * @param lab
	 *            parent lab
	 */
	public void setLab(Lab lab) {
		this.lab = lab;
	}

	/**
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return variant text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            variant text
	 */
	public void setText(String text) {
		this.text = text;
	}
}
