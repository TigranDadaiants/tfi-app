package ru.sstu.vec.core.domain;

import java.io.Serializable;

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
 * {@code Picture} class represents picture resource.
 *
 * @author Denis A. Murashev
 * @since VEC 1.1
 */
@Entity
@Table(name = "PICTURES")
public class Picture implements Serializable {

	/**
	 * Image name length.
	 */
	public static final int NAME = 50;

	/**
	 * Common type length.
	 */
	public static final int TYPE = 10;

	private static final long serialVersionUID = -524230739638350900L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PICTURE_ID_PK")
	private long id = -1L;

	@Column(name = "PICTURE_NAME", nullable = false, length = NAME)
	private String name = "";

	@Column(name = "PICTURE_TYPE", nullable = false, length = TYPE)
	private String type = "";

	@Lob
	@Column(name = "PICTURE_DATA", nullable = false)
	private byte[] data;

	@ManyToOne
	@JoinColumn(name = "LAB_ID_FK", nullable = false)
	private Lab lab;

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
	 * Gets the VEC object's name.
	 *
	 * @return VEC object name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets image name.
	 *
	 * @param name image name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Provides image type.
	 *
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets image type.
	 *
	 * @param type type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Provides image data.
	 *
	 * @return image data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Sets image data.
	 *
	 * @param data image data
	 */
	public void setData(byte[] data) {
		this.data = data;
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
}
