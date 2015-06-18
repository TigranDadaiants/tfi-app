package ru.sstu.vec.core.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * {@code Lab} class represents lab in the VEC system.
 *
 * @author Denis A. Murashev
 * @author Dmitry V. Petrov
 * @since VEC 1.0
 */
@Entity
@Table(name = "LABS")
public class Lab implements Serializable {

    /**
     * Lab name length.
     */
    public static final int NAME_LENGTH = 200;

    private static final long serialVersionUID = 5403625397709357252L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LAB_ID_PK")
    private long id = -1L;

    @Column(name = "LAB_NAME", nullable = false, length = NAME_LENGTH)
    private String name = "";

    @ManyToOne
    @JoinColumn(name = "COURSE_ID_FK", nullable = false)
    private Course course;

    @Column(name = "LAB_INDEX", nullable = false)
    private int index = -1;

    @Lob
    @Column(name = "LAB_TEXT", nullable = false)
    private String text = "";

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "LECT_LAB", joinColumns = { @JoinColumn(name = "LAB_ID_FK", referencedColumnName = "LAB_ID_PK") }, inverseJoinColumns = { @JoinColumn(name = "LECT_ID_FK", referencedColumnName = "LECT_ID_PK") })
    private List<Lecture> lectures = Collections.emptyList();

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
     * @return the name
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
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * @param course
     *            the course to set
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * @return task's index in course
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets task's index in course.
     *
     * @param index
     *            index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Provides lab description text.
     *
     * @return lab description text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets lab description text.
     *
     * @param text
     *            text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return
     */
    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int offset = 32;
        return (int) (id ^ (id >>> offset));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Lab other = (Lab) obj;
        return id == other.id;
    }
}
