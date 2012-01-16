package ru.sstu.sms.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.sms.dao.MessageDao;
import ru.sstu.sms.domain.Message;
import ru.sstu.sms.domain.MessageStatus;
import ru.sstu.sms.domain.Person;

/**
 * <code>MessageDaoImpl</code> class is {@link MessageDao} interface
 * implementation.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
@Repository("messageDao")
class MessageDaoImpl extends GenericDao<Message> implements MessageDao {

	private static final long serialVersionUID = 6578158946406295428L;

	/**
	 * {@inheritDoc}
	 */
	public List<Message> find(MessageStatus status) {
		return list(getCriteria().add(Restrictions.eq("status", status)));
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Person person) {
		delete(getCriteria().add(Restrictions.eq("person", person)));
	}
}
