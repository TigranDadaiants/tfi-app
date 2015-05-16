package ru.sstu.vec.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * {@code Attachment} class represents file resource.
 *
 * @author Denis A. Murashev
 * @author Dmitry V. Petrov
 * @since VEC 1.2
 */
@Entity
@Table(name = "ATTACHMENTS")
public class Attachment implements Serializable {

	/**
	 * Common name length.
	 */
	public static final int NAME = 50;

	private static final long serialVersionUID = 8320546240670219256L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ATTACHMENT_ID_PK")
	private long id = -1L;

	@Column(name = "ATTACHMENT_NAME", nullable = false, length = NAME)
	private String name = "";

	@Lob
	@Column(name = "ATTACHMENT_DATA", nullable = false)
	private byte[] data;

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
	 * @return the file data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data the file data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
}
