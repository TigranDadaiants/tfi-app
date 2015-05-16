package ru.sstu.vec.core.dao.impl;

import org.springframework.stereotype.Repository;

import ru.sstu.vec.core.dao.AttachmentDao;
import ru.sstu.vec.core.domain.Attachment;

/**
 * {@code AttachmentDaoImpl} class is {@link AttachmentDao} implementation.
 *
 * @author Denis A. Murashev
 * @see Attachment
 * @since VEC 1.0
 */
@Repository("attachmentDao")
class AttachmentDaoImpl extends GenericDao<Attachment>
		implements AttachmentDao {

	private static final long serialVersionUID = -7260250942656131588L;
}
