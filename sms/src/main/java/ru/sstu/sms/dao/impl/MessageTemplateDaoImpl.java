package ru.sstu.sms.dao.impl;

import org.springframework.stereotype.Repository;

import ru.sstu.sms.dao.MessageTemplateDao;
import ru.sstu.sms.domain.MessageTemplate;

/**
 * <code>MessageTemplateDaoImpl</code> class is {@link MessageTemplateDao}
 * interface implementation.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
@Repository("messageTemplateDao")
class MessageTemplateDaoImpl extends GenericDao<MessageTemplate>
		implements MessageTemplateDao {

	private static final long serialVersionUID = -3146978757509242152L;
}
