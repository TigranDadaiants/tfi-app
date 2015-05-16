package ru.sstu.vec.core.dao.impl.jcr;

import java.io.InputStream;

import javax.jcr.Node;

import ru.sstu.vec.core.dao.jcr.JcrFileDao;
import ru.sstu.vec.core.domain.jcr.JcrFile;

public class JcrFileDaoImpl implements JcrFileDao {

    @Override
    public Node saveFile(String path, JcrFile fileData, InputStream stream) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JcrFile getFile(String path) {
        // TODO Auto-generated method stub
        return null;
    }

}
