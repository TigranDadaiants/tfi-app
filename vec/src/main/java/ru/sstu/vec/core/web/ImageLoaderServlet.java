package ru.sstu.vec.core.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ru.sstu.vec.core.dao.PictureDao;
import ru.sstu.vec.core.domain.Picture;
import ru.sstu.web.core.AbstractResourceLoaderServlet;

/**
 * {@code ImageLoaderServlet} class loads image resource from data base.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public class ImageLoaderServlet extends AbstractResourceLoaderServlet {

	private static final long serialVersionUID = -8849484188542181623L;

	private static final Map<String, String> TYPES
			= new HashMap<String, String>();

	static {
		register("image/gif", "GIF");
		register("image/jpeg", "JPG", "JPEG", "JPE");
		register("image/tiff", "TIF", "TIFF");
		register("image/png", "PNG");
		register("image/ief", "IEF");
		register("image/bmp", "BMP");
		register("image/x-cmu-raster", "RAS");
		register("image/x-portable-anymap", "PNM");
		register("image/x-portable-bitmap", "PBM");
		register("image/x-portable-graymap", "PGM");
		register("image/x-portable-pixmap", "PPM");
		register("image/x-rgb", "RGB");
		register("image/x-xbitmap", "XBM");
		register("image/x-xpixmap", "XPM");
		register("image/x-xwindowdump", "XWD");
		register("application/pdf", "PDF");
		register("application/x-msmetafile", "WMF");
		register("image/svg+xml", "SVG");
	}

	@Override
	protected Object getObject(HttpServletRequest request) {
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		PictureDao imageDao = (PictureDao) context.getBean("pictureDao");
		Long id = Long.parseLong(request.getParameter("id"));
		return imageDao.findById(id);
	}

	@Override
	protected String getMimeType(Object object) {
		String type = ((Picture) object).getType().toUpperCase();
		String mimeType = TYPES.get(type);
		if (mimeType == null) {
			return "application/octet-stream";
		}
		return mimeType;
	}

	@Override
	protected void write(Object object, OutputStream output)
			throws IOException {
		Picture image = (Picture) object;
		output.write(image.getData());
	}

	/**
	 * Registers MIME types for extensions.
	 *
	 * @param mimeType   MIME type
	 * @param extensions file extensions
	 */
	private static void register(String mimeType, String... extensions) {
		for (String e : extensions) {
			TYPES.put(e, mimeType);
		}
	}
}
