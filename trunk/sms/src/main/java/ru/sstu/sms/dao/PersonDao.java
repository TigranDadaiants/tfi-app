package ru.sstu.sms.dao;

import ru.sstu.dao.Dao;
import ru.sstu.sms.domain.Person;

/**
 * <code>PersonDao</code> interface is DAO for {@link Person} objects.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
public interface PersonDao extends Dao<Person> {

	/**
	 * Searches for person with given phone.
	 *
	 * @param phone phone
	 * @return person
	 */
	Person find(String phone);
}
