package ru.sstu.vec.core.web;

import java.io.IOException;
import java.util.Calendar;

import javax.annotation.Resource;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.JcrFile;

/**
 * {@code ExpertFileController} class is controller for file editing.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.1
 */
@Controller("expertFileBean")
@Scope("session")
public class ExpertFileController extends AbstractFileController {

    private static final long serialVersionUID = -539018900332099170L;

    @Resource
    private ExpertCourseController expertCourseBean;

    protected Course getCourse() {
        return expertCourseBean.getItem();
    }

    public void upload(FileUploadEvent event) throws IOException {
        if (getCourse() == null)
            return;
        UploadedFile file = event.getFile();
        JcrFile jcrFile = new JcrFile();
        jcrFile.setName(file.getFileName());
        jcrFile.setMimeType(file.getContentType());
        jcrFile.setCreated(Calendar.getInstance());
        jcrFile.setCreatedBy(getLoggedUser().getName());
        getManager().save(getCourse(), jcrFile, file.getInputstream());
    }

    public void delete() {
        getManager().delete(getCourse(), getItem().getName());
    }

}
