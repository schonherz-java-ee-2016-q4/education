package hu.neuron.java.service.queue.reciver.mdb;

import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import hu.neuron.java.service.MessageServiceLocal;
import hu.neuron.java.service.UserServiceLocal;
import hu.neuron.java.service.queue.vo.BadWordMessageResponse;
import hu.neuron.java.service.vo.MessageVO;
import hu.neuron.java.service.vo.UserVO;

/**
 * Message-Driven Bean implementation class for: BadWordRosponseMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/BadWordQueueOut"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })

public class BadWordRosponseMDB implements MessageListener {

	@EJB
	MessageServiceLocal messageServiceLocal;

	@EJB
	UserServiceLocal userServiceLocal;

	/**
	 * Default constructor.
	 */
	public BadWordRosponseMDB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	@Override
	public void onMessage(Message message) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			BadWordMessageResponse response;

			response = (BadWordMessageResponse) objectMessage.getObject();

			MessageVO messageVO = new MessageVO();
			messageVO.setSendDate(new Date());

			UserVO user = userServiceLocal.findById(response.getUserId());
			UserVO targetUser = userServiceLocal.findById(response.getTargetUserId());

			messageVO.setTargetUser(user);
			messageVO.setSendUser(targetUser);

			messageVO.setContent(" Please, don't use the next word: " + response.getBadWord());

			messageServiceLocal.send(messageVO);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
