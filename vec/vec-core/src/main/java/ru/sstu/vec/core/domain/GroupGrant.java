package ru.sstu.vec.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * {@code GroupGrant} class holds grants for given {@link Group}.
 * Possible grants are for curator and student.
 *
 * @author denis_murashev
 * @since VEC 2.0
 */
@Entity
@Table(name = "GROUP_GRANTS")
public class GroupGrant implements Serializable {

	private static final long serialVersionUID = -4709260829372297240L;

	@EmbeddedId
	private CompositeId id = new CompositeId();

	@Column(name = "IS_OBSERVER")
	private boolean observer;

	@Column(name = "IS_STUDENT")
	private boolean student;

	/**
	 * @return the user
	 */
	public User getUser() {
		return id.user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		id.user = user;
	}

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return id.group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		id.group = group;
	}

	/**
	 * @return the observer
	 */
	public boolean isObserver() {
		return observer;
	}

	/**
	 * @param observer the observer to set
	 */
	public void setObserver(boolean observer) {
		this.observer = observer;
	}

	/**
	 * @return the student
	 */
	public boolean isStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(boolean student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return id.user + " for " + id.group  + ": "
				+ (observer ? "observer " : "")
				+ (student ? "student" : "");
	}

	/**
	 * This is just composite key representation.
	 *
	 * @author Denis_Murashev
	 */
	private static class CompositeId implements Serializable {

		private static final long serialVersionUID = -4584730497409879330L;

		@ManyToOne
		@JoinColumn(name = "USER_ID_FK", nullable = false)
		private User user;

		@ManyToOne
		@JoinColumn(name = "GROUP_ID_FK", nullable = false)
		private Group group;
	}
}
