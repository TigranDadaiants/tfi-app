package ru.sstu.vec.core.domain;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;

import javax.jcr.Binary;
import javax.jcr.RepositoryException;

import ru.sstu.vec.core.util.JcrException;

public class JcrFile implements Serializable {

    private static final long serialVersionUID = -5473124365722001797L;

    private String name;

    private String mimeType;

    private Calendar created;

    private String createdBy;

    private Binary data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Binary getData() {
        return data;
    }

    public void setData(Binary data) {
        this.data = data;
    }

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
