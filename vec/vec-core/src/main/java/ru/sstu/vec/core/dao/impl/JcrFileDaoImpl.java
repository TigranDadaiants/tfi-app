package ru.sstu.vec.core.dao.impl;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.util.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.JcrFileDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.JcrFile;
import ru.sstu.vec.core.util.JcrException;

@Repository("jcrFileDao")
public class JcrFileDaoImpl implements JcrFileDao {

    private static final String JCR_CREATED = "jcr:created";
    private static final String JCR_CREATED_BY = "jcr:createdBy";
    private static final String JCR_MIME_TYPE = "jcr:mimeType";
    private static final String JCR_LAST_MODIFIED = "jcr:lastModified";
    private static final String JCR_DATA = "jcr:data";
    private static final String JCR_CONTENT = "jcr:content";
    private static final String NT_RESOURCE = "nt:resource";
    private static final String NT_FILE = "nt:file";
    private static final String NT_FOLDER = "nt:folder";

    private static final String COURSE_ROOT = "courses";

    private final Log log = LogFactory.getLog(JcrFileDaoImpl.class);

    @Autowired
    private Session session;

    @Override
    public Node save(Node folder, JcrFile fileData, InputStream stream)
            throws JcrException {
        Node file;
        try {
            file = folder.addNode(escape(fileData.getName()), NT_FILE);
            if (file.hasProperty(JCR_CREATED_BY)) {
                file.setProperty(JCR_CREATED_BY, fileData.getCreatedBy());
            }
            Node content = file.addNode(JCR_CONTENT, NT_RESOURCE);
            content.setProperty(JCR_LAST_MODIFIED, fileData.getCreated());
            content.setProperty(JCR_MIME_TYPE, fileData.getMimeType());
            Binary binary = session.getValueFactory().createBinary(stream);
            content.setProperty(JCR_DATA, binary);
            session.save();
            return file;
        } catch (RepositoryException e) {
            throw new JcrException(e);
        }
    }

    private Node getCourseRoot() throws RepositoryException {
        return _getOrCreateFolder(session.getRootNode(), COURSE_ROOT);
    }

    private String getCourseNodeName(Course course) {
        return Long.toString(course.getId());
    }

    private String escape(String fileName) {
        return Text.escapeIllegalJcrChars(fileName);
    }

    @Override
    public JcrFile find(Course course, String name) throws JcrException {
        Node fileNode;
        name = escape(name);
        try {
            Node root = getCourseRoot();
            String courseNode = getCourseNodeName(course);
            if (root.hasNode(courseNode)) {
                Node folder = root.getNode(courseNode);
                if (folder.hasNode(name)) {
                    fileNode = folder.getNode(name);
                    return getFile(fileNode);
                }
            }
            return null;
        } catch (RepositoryException e) {
            throw new JcrException(e);
        }

    }

    @Override
    public List<JcrFile> find(Course course) throws JcrException {
        try {
            NodeIterator iter = _getOrCreateFolder(course).getNodes();
            List<JcrFile> files = new LinkedList<JcrFile>();
            while (iter.hasNext()) {
                files.add(getFile(iter.nextNode()));
            }
            return files;
        } catch (RepositoryException e) {
            throw new JcrException(e);
        }
    }

    private JcrFile getFile(Node fileNode) throws RepositoryException {
        JcrFile file = new JcrFile();
        file.setName(fileNode.getName());
        file.setCreated(fileNode.getProperty(JCR_CREATED).getDate());
        file.setCreatedBy(fileNode.getProperty(JCR_CREATED_BY).getString());
        Node content = fileNode.getNode(JCR_CONTENT);
        file.setMimeType(content.getProperty(JCR_MIME_TYPE).getString());
        file.setData(content.getProperty(JCR_DATA).getBinary());
        return file;
    }

    public Node _getOrCreateFolder(Course course) throws RepositoryException {
        return _getOrCreateFolder(getCourseRoot(),
                Long.toString(course.getId()));
    }

    private Node _getOrCreateFolder(Node parent, String name)
            throws RepositoryException {
        if (parent.hasNode(name)) {
            return parent.getNode(name);
        } else {
            Node folder = parent.addNode(name, NT_FOLDER);
            session.save();
            return folder;
        }
    }

    private Node _getFolder(Course course) throws RepositoryException {
        Node root = session.getRootNode();
        String courseNode = getCourseNodeName(course);
        if (root.hasNode(courseNode)) {
            return root.getNode(courseNode);
        }
        return null;
    }

    public Node getFolder(Course course) throws JcrException {
        try {
            return _getFolder(course);
        } catch (RepositoryException e) {
            throw new JcrException(e);
        }
    }

    @Override
    public boolean isFileExists(Course course, String name) throws JcrException {
        try {
            Node root = getCourseRoot();
            name = escape(name);
            String courseNode = getCourseNodeName(course);
            if (root.hasNode(courseNode)) {
                Node folder = root.getNode(courseNode);
                if (folder.hasNode(name))
                    return true;
                return false;
            } else {
                return false;
            }
        } catch (RepositoryException e) {
            throw new JcrException(e);
        }
    }

    @Override
    public void delete(Course course, String name) throws JcrException {
        try {
            name = escape(name);
            Node folder = _getOrCreateFolder(course);
            if (folder.hasNode(name)) {
                Node file;
                file = folder.getNode(name);
                file.remove();
                session.save();
            }
        } catch (RepositoryException e) {
            throw new JcrException(e);
        }
    }

    @Override
    public void delete(Course course) throws JcrException {
        try {
            Node folder = _getFolder(course);
            if (folder != null) {
                folder.remove();
                session.save();
            }
        } catch (RepositoryException e) {
            throw new JcrException(e);
        }
    }

    @Override
    public Node getOrCreateFolder(Course course) throws JcrException {
        try {
            return _getOrCreateFolder(course);
        } catch (RepositoryException e) {
            throw new JcrException(e);
        }
    }

    @Override
    public Node getOrCreateFolder(Node parent, String name) throws JcrException {
        try {
            return _getOrCreateFolder(parent, name);
        } catch (RepositoryException e) {
            throw new JcrException(e);
        }
    }
}
