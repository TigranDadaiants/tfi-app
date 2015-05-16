package ru.sstu.vec.core.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.docs.DocumentException;
import ru.sstu.vec.core.domain.Lecture;
import ru.sstu.vec.core.service.ExpertLectureManager;
import ru.sstu.vec.core.service.ItemManager;
import ru.sstu.vec.core.service.LectureImporter;
import ru.sstu.vec.core.service.LectureImporterFactory;
import ru.sstu.vec.core.service.model.DocFileFormat;
import ru.sstu.vec.core.web.util.FileUtil;

/**
 * {@code ExpertLectureController} class is controller for lecture editing.
 *
 * @author Tigran Dadaiants
 * @since VEC 2.1
 */
@Controller("expertLectureBean")
@Scope("session")
public class ExpertLectureController extends AbstractItemController<Lecture> {

    private static final long serialVersionUID = 7437950548869616722L;

    private static Logger log = Logger.getLogger(ExpertLectureController.class);

    private static final String ERROR_UPLOAD_MESSAGE = "Cannot read uploaded file";

    @Resource
    private ExpertLectureManager expertLectureManager;

    @Resource
    private ExpertCourseController expertCourseBean;

    @Resource
    private LectureImporterFactory lectureImporterFactory;

    private DocFileFormat format = DocFileFormat.DOCX;

    public void importLecture(FileUploadEvent event) {
        try {
            DocFileFormat fileFormat = FileUtil.getDocFileFormat(event);
            if (fileFormat != null) {
                format = fileFormat;
                LectureImporter importer = lectureImporterFactory.get(format);
                InputStream input = event.getFile().getInputstream();
                importer.importLecture(expertCourseBean.getItem(), input);
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

    @Override
    protected ItemManager<Lecture> getManager() {
        expertLectureManager.setCourse(expertCourseBean.getItem());
        return expertLectureManager;
    }

    @Override
    protected Lecture newItem() {
        Lecture lecture = new Lecture();
        lecture.setCourse(expertCourseBean.getItem());
        return lecture;
    }

    @Override
    protected String getListViewId() {
        return "expertCourseInfo";
    }

    @Override
    protected String getItemViewId() {
        return "expertLectureInfo";
    }

}
