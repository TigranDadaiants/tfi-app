package ru.sstu.vec.core.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * {@code UserTheme} class represents user and theme match in the VEC system.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.0
 */
@Entity
@Table(name = "USER_THEMES")
public class UserTheme implements Serializable {
	/**
	 *serialVersionUID
	 */
	private static final long serialVersionUID = 4073400785415741570L;

	@GenericGenerator(name = "generator", strategy = "foreign",
	parameters = @Parameter(name = "property", value = "user"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "USER_ID_PK", unique = true, nullable = false)
	private long userId;

	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	@ManyToOne
	@JoinColumn(name = "THEME_NAME", nullable = false)
	private Theme theme;

	/**
	 * @return id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId id to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return theme
	 */
	public Theme getTheme() {
		return theme;
	}

	/**
	 * @param theme theme to set
	 */
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
}
