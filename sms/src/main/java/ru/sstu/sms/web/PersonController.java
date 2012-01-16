package ru.sstu.sms.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.richfaces.event.UploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.sms.dao.MessageDao;
import ru.sstu.sms.dao.PersonDao;
import ru.sstu.sms.domain.Person;
import ru.sstu.sms.service.PersonImporter;
import ru.sstu.sms.service.model.FileFormat;
import ru.sstu.tables.TableException;
import ru.sstu.web.jsf.JsfController;

/**
 * <code>PersonController</code> class manages persons list in the system.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
@Controller("persons")
@Scope("session")
public class PersonController extends JsfController {

	private static final long serialVersionUID = -1299528390922312401L;

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(PersonController.class);

	/**
	 * DAO resource for persons.
	 */
	@Resource
	private PersonDao personDao;

	/**
	 * DAO resource for messages.
	 */
	@Resource
	private MessageDao messageDao;

	/**
	 * Person importer resource.
	 */
	@Resource
	private PersonImporter personImporter;

	/**
	 * SMS controller.
	 */
	@Resource
	private SmsController smsSender;

	/**
	 * Current person.
	 */
	private Person person;

	/**
	 * Selected file format.
	 */
	private FileFormat format = FileFormat.XLS;

	/**
	 * @return all available disciplines
	 */
	public List<Person> getAll() {
		return personDao.find();
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return possible file formats for uploaded lab
	 */
	public List<SelectItem> getFormats() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (FileFormat f : FileFormat.values()) {
			items.add(new SelectItem(f, getMessages().getString(f.getKey())));
		}
		return items;
	}

	/**
	 * @return the format
	 */
	public FileFormat getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(FileFormat format) {
		this.format = format;
	}

	/**
	 * Adds new person.
	 *
	 * @return view id
	 */
	public String add() {
		person = new Person();
		return null;
	}

	/**
	 * Edits existing person.
	 *
	 * @return view id
	 */
	public String edit() {
		return null;
	}

	/**
	 * Saves current person.
	 *
	 * @return view id
	 */
	public String save() {
		personDao.save(person);
		smsSender.updatePersons();
		return null;
	}

	/**
	 * Deletes current person.
	 *
	 * @return <code>null</code>
	 */
	public String delete() {
		messageDao.delete(person);
		personDao.delete(person);
		smsSender.updatePersons();
		return null;
	}

	/**
	 * Imports persons list.
	 *
	 * @param event upload event
	 */
	public void importPersons(UploadEvent event) {
		InputStream input = null;
		try {
			input = new FileInputStream(event.getUploadItem().getFile());
			personImporter.importPersons(format, input);
			smsSender.updatePersons();
		} catch (FileNotFoundException e) {
			log.error("Uploaded file is not found", e);
		} catch (TableException e) {
			log.error("Cannot read data from file", e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.error("Cannot close file", e);
				}
			}
		}
	}
}
