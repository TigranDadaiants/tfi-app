package ru.sstu.vec.core.service.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * <code>DocFileFormat</code> enum contains supported formats for documents
 * importing.
 *
 * @author Denis_Murashev
 * @author Tigran_Dadaiants
 * @since VEC 2.1 (renamed from 2.0 LabFileFormat)
 */
public enum DocFileFormat {

    /**
     * MS Word 2003 XML format.
     */
    DOC_XML("file.format.doc.xml", "doc,xml,docxml"),

    /**
     * MS Word 2007 format.
     */
    DOCX("file.format.docx", "docx");

    private static final Map<String, DocFileFormat> formatMap = new HashMap<String, DocFileFormat>();

    private final static Logger log = Logger.getLogger(DocFileFormat.class);

    static {
        for (DocFileFormat format : values()) {
            String types = format.types;
            if (types.contains(",")) {
                String[] typesArr = types.split(",");
                for (String type : typesArr) {
                    formatMap.put(type, format);
                }
            } else {
                formatMap.put(format.types, format);
            }
        }
    }
    /**
     * Key for description.
     */
    private final String key;

    /**
     * Accepted types.
     */
    private final String types;

    /**
     * @param key
     *            key
     * @param types
     *            accepted types
     */
    private DocFileFormat(String key, String types) {
        this.key = key;
        this.types = types;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the types
     */
    public String getTypes() {
        return types;
    }

    /**
     * Returns <code>DocFileFormat</code> for given file extension or
     * <b>null</b> if such extension not found.
     *
     * @param ext
     *            file extension
     * @return format or <b>null</b>
     */
    public static DocFileFormat getFormatByExtension(String ext) {
        log.debug("getFormatByExtension(); extension: " + ext + "format: "
                + formatMap.get(ext));
        return formatMap.get(ext);
    }
}
