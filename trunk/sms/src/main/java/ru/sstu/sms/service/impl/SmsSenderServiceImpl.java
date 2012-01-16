package ru.sstu.sms.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.TimeoutException;
import org.smslib.Message.MessageEncodings;
import org.smslib.modem.SerialModemGateway;
import org.springframework.stereotype.Service;

import ru.sstu.sms.dao.MessageDao;
import ru.sstu.sms.dao.MessageTemplateDao;
import ru.sstu.sms.domain.Message;
import ru.sstu.sms.domain.MessageStatus;
import ru.sstu.sms.domain.MessageTemplate;
import ru.sstu.sms.domain.Person;
import ru.sstu.sms.service.SmsSenderService;
import ru.sstu.sms.service.model.CheckableObject;

/**
 * <code>SmsSenderServiceImpl</code> class is {@link SmsSenderService}
 * interface implementation.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
@Service("smsSenderService")
class SmsSenderServiceImpl implements SmsSenderService, Serializable {

	private static final long serialVersionUID = -6027057662102916440L;

	/**
	 * Logger.
	 */
	private static Logger log = Logger.getLogger(SmsSenderServiceImpl.class);

	/**
	 * Error message.
	 */
	private static final String ERROR_MESSAGE = "Cannot send message";

	/**
	 * Message DAO resource.
	 */
	@Resource
	private MessageDao messageDao;

	/**
	 * Message template DAO resource.
	 */
	@Resource
	private MessageTemplateDao messageTemplateDao;

	private transient org.smslib.Service service
			= org.smslib.Service.getInstance();

	/**
	 * Creates SMS Sender service.
	 */
	public SmsSenderServiceImpl() {
		InputStream input = null;
		try {
			Properties properties = new Properties();
			input = getClass().getResourceAsStream("/modem.properties");
			properties.load(input);
			SerialModemGateway gateway = new SerialModemGateway(
				properties.getProperty("modem.id"),
				properties.getProperty("modem.com.port"),
				Integer.valueOf(properties.getProperty("modem.baud.rate")),
				properties.getProperty("modem.manufacturer"),
				properties.getProperty("modem.model"));
			gateway.setInbound(true);
			gateway.setOutbound(true);
			gateway.setSimPin(properties.getProperty("modem.pin.code"));
			service.addGateway(gateway);
		} catch (GatewayException e) {
			log.error(ERROR_MESSAGE, e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.error(ERROR_MESSAGE, e);
			throw new RuntimeException(e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					log.error(ERROR_MESSAGE, e);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean prepare(String text, List<CheckableObject<Person>> persons) {
		MessageTemplate template = new MessageTemplate();
		template.setText(text);
		messageTemplateDao.save(template);
		for (CheckableObject<Person> p : persons) {
			if (p.isChecked()) {
				Message message = new Message();
				message.setPerson(p.getObject());
				message.setTemplate(template);
				messageDao.save(message);
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public void send() {
		new Thread(new Runnable() {
			public void run() {
				List<Message> messages = messageDao.find(MessageStatus.NEW);
				send(messages);
			}
		}).start();
	}

	/**
	 * Sends list of messages.
	 *
	 * @param messages messages
	 */
	private void send(List<Message> messages) {
		try {
			service.startService();
			for (Message m : messages) {
				Person person = m.getPerson();
				OutboundMessage message = new OutboundMessage(person.getPhone(),
						processMessageText(m.getTemplate().getText(), person));
				message.setEncoding(MessageEncodings.ENCUCS2);
				service.sendMessage(message);
				m.setDateSent(new Date());
				m.setStatus(MessageStatus.SENT);
				messageDao.save(m);
			}
			service.stopService();
		} catch (GatewayException e) {
			log.error(ERROR_MESSAGE, e);
		} catch (TimeoutException e) {
			log.error(ERROR_MESSAGE, e);
		} catch (SMSLibException e) {
			log.error(ERROR_MESSAGE, e);
		} catch (IOException e) {
			log.error(ERROR_MESSAGE, e);
		} catch (InterruptedException e) {
			log.error(ERROR_MESSAGE, e);
		}
	}

	/**
	 * Process message text for given person.
	 *
	 * @param text   text template
	 * @param person person
	 * @return processed message
	 */
	private String processMessageText(String text, Person person) {
		return text.replaceAll("<!--FIRST_NAME-->", person.getFirstName());
	}
}
