package ru.sstu.vec.core.dao.jcr;

import java.io.InputStream;
import javax.jcr.Node;
import ru.sstu.vec.core.domain.jcr.JcrFile;

public interface JcrFileDao {

    public Node saveFile(String path, JcrFile fileData, InputStream stream);

    public JcrFile getFile(String path);

}
