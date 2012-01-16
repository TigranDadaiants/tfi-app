package ru.sstu.sms.service;

import java.io.InputStream;

import ru.sstu.sms.service.model.FileFormat;
import ru.sstu.tables.TableException;

/**
 * <code>PersonImporter</code> interface.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
public interface PersonImporter {

	/**
	 * Imports persons list from given input stream.
	 *
	 * @param format file format
	 * @param input  input stream
	 * @throws TableException if cannot read data
	 */
	void importPersons(FileFormat format, InputStream input)
			throws TableException;
}
