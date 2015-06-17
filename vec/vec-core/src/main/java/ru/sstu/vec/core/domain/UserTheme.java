package ru.sstu.vec.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
     * serialVersionUID
     */
    private static final long serialVersionUID = 4073400785415741570L;

    @Id
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "THEME_NAME")
    private String theme;

    /**
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
