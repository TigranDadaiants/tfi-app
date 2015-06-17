package ru.sstu.vec.core.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.JcrFile;
import ru.sstu.vec.core.service.JcrFileManager;
import ru.sstu.vec.core.web.util.FileUtil;

public abstract class AbstractFileController extends VecController {

    private static final long serialVersionUID = -6118850226633854941L;

    @Resource
    private JcrFileManager jcrFileManager;

    private JcrFile item;

    public String download() {
        if (item == null)
            return null;
        HttpServletResponse response = getContext().getResponse();
        FileUtil.startFileDownload(item, response);
        complete();
        return null;
    }

    public List<JcrFile> getAll() {
        return jcrFileManager.find(getCourse());
    }

    protected abstract Course getCourse();

    public JcrFileManager getManager() {
        return jcrFileManager;
    }

    public JcrFile getItem() {
        return item;
    }

    public void setItem(JcrFile item) {
        this.item = item;
    }

}
