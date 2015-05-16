package ru.sstu.vec.core.service.impl;

import org.springframework.stereotype.Service;

import ru.sstu.docs.DocXmlReader;
import ru.sstu.docs.DocumentReader;

/**
 * {@code DocXmlLabImporter} class is used for importing {@code Lab}
 * data from MS Word 2003 XML format files.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
@Service("docXmlLabImporter")
class DocXmlLabImporter extends AbstractLabImporter {

	@Override
	protected DocumentReader getReader() {
		return new DocXmlReader();
	}
}
