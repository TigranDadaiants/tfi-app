package ru.sstu.vec.core.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.util.StringUtils;

import ru.sstu.vec.core.domain.JcrFile;
import ru.sstu.vec.core.service.model.DocFileFormat;

/**
 * Utility for work with files.
 *
 * @author Tigran_Dadaiants
 * @since VEC 2.1
 */
public class FileUtil {

    private static final int DEFAULT_BUFFER_SIZE = 10240;

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

    public static void startFileDownload(JcrFile item,
            HttpServletResponse response) {
        startFileDownload(item, response, DEFAULT_BUFFER_SIZE);
    }

    public static void startFileDownload(JcrFile item,
            HttpServletResponse response, int bufferSize) {
        OutputStream output = null;
        InputStream input = null;
        try {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + item.getName());
            output = response.getOutputStream();
            input = item.getStream();
            write(input, output, bufferSize);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                    item.getData().dispose();
                }
                if (output != null)
                    output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void write(InputStream in, OutputStream out, int bufferSize)
            throws IOException {
        byte[] buffer = new byte[bufferSize];
        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
    }

}
