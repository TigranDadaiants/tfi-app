package ru.sstu.vec.core.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;

/**
 * {@code Theme} class represents theme in the VEC system.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.0
 */
@Entity
@Table(name = "THEMES")
public class Theme implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5768900716468527568L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "THEME_ID")
	private long id = -1L;

	@Column(name = "THEME_NAME", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "theme")
	private List<UserTheme> userThemes = Collections.emptyList();

	public Theme() {
	}

	public Theme(String name) {
		super();
		this.name = name;
	}

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
	 * @return name
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
	 * @return list of userThemes for this user
	 */
	public List<UserTheme> getUserThemes() {
		return userThemes;
	}

	/**
	 * @param userThemes
	 *            list of userTheme to set
	 */
	public void setUserThemes(List<UserTheme> userThemes) {
		this.userThemes = userThemes;
	}

	@Override
	public String toString() {
		return name;
	}
}
