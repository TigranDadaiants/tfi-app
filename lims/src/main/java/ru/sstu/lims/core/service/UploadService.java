package ru.sstu.lims.core.service;


/**
 * {@code UploadService} interface describes contract for data uploading.
 *
 * @author denis_murashev
 * @since LIMS 1.0
 */
public interface UploadService {

	/**
	 * Reads and saves data from given input stream.
	 *
	 * @param data uploaded data
	 * @param type uploaded data format type
	 */
	void upload(byte[] data, DataType type);

	/**
	 * {@code DataType} enumeration describes data formats.
	 *
	 * @author denis_murashev
	 * @since LIMS 1.0
	 */
	public enum DataType {
		/**
		 * Old Excel format (2003 and before).
		 */
		XLS,

		/**
		 * New Excel format (2007 and newer).
		 */
		XLSX
	}
}
