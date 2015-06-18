package ru.sstu.vec.core.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.docs.DocumentException;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.Lecture;
import ru.sstu.vec.core.service.ExpertLabManager;
import ru.sstu.vec.core.service.LabImporter;
import ru.sstu.vec.core.service.LabImporterFactory;
import ru.sstu.vec.core.service.ExpertLectureManager;
import ru.sstu.vec.core.service.model.DocFileFormat;
import ru.sstu.vec.core.web.util.FileUtil;

/**
 * {@code ExpertLabController} class is controller for lab editing.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Controller("expertLabBean")
@Scope("session")
public class ExpertLabController extends AbstractItemController<Lab> {

    private static final long serialVersionUID = 1266309652663054399L;

    private static Logger log = Logger.getLogger(ExpertLabController.class);

    private static final String ERROR_UPLOAD_MESSAGE = "Cannot read uploaded file";

    @Resource
    private ExpertLabManager expertLabManager;

    @Resource
    private ExpertLectureManager expertLectureManager;

    @Resource
    private LabImporterFactory labImporterFactory;

    @Resource
    private ExpertCourseController expertCourseBean;

    private DocFileFormat format = DocFileFormat.DOCX;

    /**
     * @return the format
     */
    public DocFileFormat getFormat() {
        return format;
    }

    /**
     * @param format
     *            the format to set
     */
    public void setFormat(DocFileFormat format) {
        this.format = format;
    }

    /**
     * @return possible file formats for uploaded lab
     */
    public List<DocFileFormat> getFormats() {
        return Arrays.asList(DocFileFormat.values());
    }

    /**
     * Imports lab data from uploaded file.
     *
     * @param event
     *            upload event
     */
    public void importLab(FileUploadEvent event) {
        try {
            DocFileFormat fileFormat = FileUtil.getDocFileFormat(event);
            if (fileFormat != null) {
                format = fileFormat;
                LabImporter importer = labImporterFactory.get(format);
                InputStream input = event.getFile().getInputstream();
                importer.importLab(expertCourseBean.getItem(), input);
            } else {
                log.error("DocFileFormat not found for given file!");
                return;
            }
        } catch (IOException e) {
            // TODO use growl to notify user
            log.error(ERROR_UPLOAD_MESSAGE, e);
        } catch (DocumentException e) {
            log.error(ERROR_UPLOAD_MESSAGE, e);
        }
    }

    public List<Lecture> getLectures() {
        return getItem().getLectures();
    }

    @Override
    protected ExpertLabManager getManager() {
        expertLabManager.setCourse(expertCourseBean.getItem());
        return expertLabManager;
    }

    @Override
    protected Lab newItem() {
        Lab lab = new Lab();
        lab.setCourse(expertCourseBean.getItem());
        return lab;
    }

    @Override
    protected String getListViewId() {
        return "expertCourseInfo";
    }

    @Override
    protected String getItemViewId() {
        return "expertLabInfo";
    }
}
