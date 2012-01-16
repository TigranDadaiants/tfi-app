package ru.sstu.sms.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import ru.sstu.sms.dao.PersonDao;
import ru.sstu.sms.domain.MessageTemplate;
import ru.sstu.sms.domain.Person;
import ru.sstu.sms.service.SmsSenderService;
import ru.sstu.sms.service.model.CheckableObject;
import ru.sstu.web.jsf.JsfController;

/**
 * <code>SmsController</code> class is web controller for SMS sender.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
@Controller("smsSender")
@Scope("session")
public class SmsController extends JsfController {

	private static final long serialVersionUID = -4820670771549774372L;

	/**
	 * Service for SMS sending.
	 */
	@Resource
	private SmsSenderService smsSenderService;

	/**
	 * Person DAO resource.
	 */
	@Resource
	private PersonDao personDao;

	/**
	 * Message.
	 */
	private MessageTemplate message = new MessageTemplate();

	/**
	 * List of persons.
	 */
	private List<CheckableObject<Person>> persons = null;

	/**
	 * @return the message
	 */
	public MessageTemplate getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(MessageTemplate message) {
		this.message = message;
	}

	/**
	 * @return list of persons
	 */
	public List<CheckableObject<Person>> getPersons() {
		if (persons == null) {
			persons = new ArrayList<CheckableObject<Person>>();
			for (Person p : personDao.find()) {
				persons.add(new CheckableObject<Person>(p, true));
			}
		}
		return persons;
	}

	/**
	 * Updates persons.
	 */
	public void updatePersons() {
		persons = null;
	}

	/**
	 * Sends SMS.
	 *
	 * @return <code>null</code>
	 */
	public String send() {
		FacesMessage facesMessage;
		if (smsSenderService.prepare(message.getText(), persons)) {
			smsSenderService.send();
			facesMessage = getMessages().getMessage("sms.send.success");
			facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			message.setText("");
		} else {
			facesMessage = getMessages().getMessage("sms.send.failure");
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("sendSmsForm:message", facesMessage);
		return null;
	}
}
