package ru.sstu.sms.dao;

import java.util.List;

import ru.sstu.dao.Dao;
import ru.sstu.sms.domain.Message;
import ru.sstu.sms.domain.MessageStatus;
import ru.sstu.sms.domain.Person;

/**
 * <code>MessageDao</code> interface is DAO for {@link Message} objects.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
public interface MessageDao extends Dao<Message> {

	/**
	 * Provides messages with given status.
	 *
	 * @param status status
	 * @return list of messages
	 */
	List<Message> find(MessageStatus status);

	/**
	 * Deletes all messages for given person.
	 *
	 * @param person person
	 */
	void delete(Person person);
}
