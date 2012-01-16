package ru.sstu.sms.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.sstu.sms.dao.PersonDao;
import ru.sstu.sms.domain.Person;

/**
 * <code>PersonDaoImpl</code> class is {@link PersonDao} interface
 * implementation.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
@Repository("personDao")
class PersonDaoImpl extends GenericDao<Person> implements PersonDao {

	private static final long serialVersionUID = -8133782269663813810L;

	/**
	 * {@inheritDoc}
	 */
	public Person find(String phone) {
		return unique(getCriteria().add(Restrictions.eq("phone", phone)));
	}
}
