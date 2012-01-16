package ru.sstu.sms.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import ru.sstu.sms.dao.MessageDao;
import ru.sstu.sms.domain.Message;
import ru.sstu.sms.service.SmsSenderService;
import ru.sstu.web.jsf.JsfController;

/**
 * <code>MessageController</code> class manages messages list in the system.
 *
 * @author Denis_Murashev
 * @since SMS 1.0
 */
@Controller("messages")
public class MessageController extends JsfController {

	private static final long serialVersionUID = -5220767373487120187L;

	/**
	 * Message DAO resource.
	 */
	@Resource
	private MessageDao messageDao;

	/**
	 * SMS sender service resource.
	 */
	@Resource
	private SmsSenderService smsSenderService;

	/**
	 * @return all messages
	 */
	public List<Message> getAll() {
		return messageDao.find();
	}

	/**
	 * Sends unsent messages.
	 *
	 * @return <code>null</code>
	 */
	public String send() {
		smsSenderService.send();
		return null;
	}
}
