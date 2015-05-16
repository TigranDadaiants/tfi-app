package ru.sstu.vec.core.service;

import java.io.InputStream;

import ru.sstu.docs.DocumentException;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lab;

/**
 * <code>LabImporter</code> interface is used for importing {@link Lab} data
 * from uploaded file.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
public interface LabImporter {

	/**
	 * Reads data from input stream.
	 * Creates {@link Lab} object and stores it in data base.
	 *
	 * @param course course
	 * @param input  input stream
	 * @return imported lab
	 * @throws DocumentException if cannot read input stream
	 */
	Lab importLab(Course course, InputStream input) throws DocumentException;
}
