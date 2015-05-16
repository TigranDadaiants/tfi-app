package ru.sstu.vec.core.web.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.util.StringUtils;

import ru.sstu.vec.core.service.model.DocFileFormat;

public class FileUtil {

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

    public static void startFileDownload(HttpServletResponse response,
            byte[] file, String filename) throws IOException {
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="
                + filename);
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            output.write(file);
            output.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            if (output != null)
                output.close();
        }
    }

}
