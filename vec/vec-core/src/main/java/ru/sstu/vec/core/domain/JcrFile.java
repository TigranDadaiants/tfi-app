package ru.sstu.vec.core.domain;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;

import javax.jcr.Binary;
import javax.jcr.RepositoryException;

import ru.sstu.vec.core.util.JcrException;

/**
 * {@code JcrFile} represents a File data used to work with JCR.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.1
 */
public class JcrFile implements Serializable {

    private static final long serialVersionUID = -5473124365722001797L;

    private String name;

    private String mimeType;

    private Calendar created;

    private String createdBy;

    private Binary data;

    /**
     * @return file name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            file name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return file MIME type
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType
     *            file MIME type to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * @return file creation date
     */
    public Calendar getCreated() {
        return created;
    }

    /**
     * @param created
     *            file creation date to set
     */
    public void setCreated(Calendar created) {
        this.created = created;
    }

    /**
     * @return name of file creator
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     *            file creator name to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return file content as {@link Binary} object
     */
    public Binary getData() {
        return data;
    }

    /**
     * @param data
     *            {@link Binary} object with file content to set
     */
    public void setData(Binary data) {
        this.data = data;
    }

    /**
     * @return InputStream of file data from {@link Binary} object. If Binary is
     *         null, returns <b>null</b>.
     * @throws JcrException
     */
    public InputStream getStream() throws JcrException {
        if (data != null) {
            try {
                return data.getStream();
            } catch (RepositoryException e) {
                throw new JcrException();
            }
        }
        return null;
    }

}
