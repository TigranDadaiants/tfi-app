package ru.sstu.sms.service;

import java.util.List;

import ru.sstu.sms.domain.Person;
import ru.sstu.sms.service.model.CheckableObject;

/**
 * <code>SmsSenderService</code> interface.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
public interface SmsSenderService {

	/**
	 * Prepares message to all persons.
	 *
	 * @param text    message text
	 * @param persons list of persons
	 * @return <code>true</code> if message was sent
	 */
	boolean prepare(String text, List<CheckableObject<Person>> persons);

	/**
	 * Sends unsent messages.
	 */
	void send();
}
