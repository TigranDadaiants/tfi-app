package ru.sstu.sms.service.impl;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ru.sstu.sms.dao.PersonDao;
import ru.sstu.sms.domain.Person;
import ru.sstu.sms.service.PersonImporter;
import ru.sstu.sms.service.model.FileFormat;
import ru.sstu.tables.CsvTableReader;
import ru.sstu.tables.TableException;
import ru.sstu.tables.TableReader;
import ru.sstu.tables.XlsTableReader;
import ru.sstu.tables.XlsxTableReader;
import ru.sstu.tables.XmlMapping;

/**
 * <code>AbstractPersonImporter</code> class is default abstract implementation
 * of {@link PersonImporter} interface.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
@Service("personImporter")
class PersonImporterImpl implements PersonImporter, Serializable {

	private static final long serialVersionUID = 8570053929303136824L;

	/**
	 * Supported readers.
	 */
	private static final Map<FileFormat, TableReader> READERS
			= new HashMap<FileFormat, TableReader>();

	static {
		READERS.put(FileFormat.XLS, new XlsTableReader());
		READERS.put(FileFormat.XLSX, new XlsxTableReader());
		READERS.put(FileFormat.CSV, new CsvTableReader());
	}

	/**
	 * Person DAO resource.
	 */
	@Resource
	private PersonDao personDao;

	/**
	 * {@inheritDoc}
	 */
	public void importPersons(FileFormat format, InputStream input)
			throws TableException {
		TableReader reader = READERS.get(format);
		if (reader != null) {
			XmlMapping<Person> mapping = new XmlMapping<Person>(getClass()
					.getResourceAsStream("/Person.map.xml"));
			List<Person> persons = reader.read(mapping, input);
			for (Person p : persons) {
				if (correctPhone(p)) {
					Person person = personDao.find(p.getPhone());
					if (person == null) {
						personDao.save(p);
					}
				}
			}
		}
	}

	/**
	 * Corrects person's phone number.
	 *
	 * @param person person
	 * @return <code>false</code> if phone is incorrect
	 */
	private boolean correctPhone(Person person) {
		String phone = person.getPhone();
		if (phone.matches("\\+7\\d{10}")) {
			return true;
		}
		try {
			double value = Double.valueOf(phone);
			phone = String.valueOf((long) value);
		} catch (NumberFormatException e) {
			return false;
		}
		final String code = "+7";
		if (phone.matches("8\\d{10}")) {
			person.setPhone(code + phone.substring(1));
			return true;
		}
		if (phone.matches("\\d{10}")) {
			person.setPhone(code + phone);
			return true;
		}
		return false;
	}
}
