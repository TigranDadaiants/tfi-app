package ru.sstu.vec.core.service.impl;

import org.springframework.stereotype.Service;

import ru.sstu.docs.DocumentReader;
import ru.sstu.docs.DocxReader;

/**
 * <code>DocxLabImporter</code> class is used for importing <code>Lab</code>
 * data from MS Word 2007 format files.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Service("docxLabImporter")
class DocxLabImporter extends AbstractLabImporter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DocumentReader getReader() {
		return new DocxReader();
	}
}
