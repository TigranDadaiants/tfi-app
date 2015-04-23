package ru.sstu.vec.core.web.util;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.util.StringUtils;

import ru.sstu.vec.core.service.model.DocFileFormat;

public class FileUploadUtil {

    /**
     * @param event
     *            file upload event
     * @return <code>DocFileFormat</code> for file or <b>null</b>
     * @see DocFileFormat#getFormatByExtension(String)
     */
    public static DocFileFormat getDocFileFormat(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        String ext = StringUtils.getFilenameExtension(file.getFileName());
        DocFileFormat fileFormat = DocFileFormat.getFormatByExtension(ext);
        return fileFormat;
    }

}
