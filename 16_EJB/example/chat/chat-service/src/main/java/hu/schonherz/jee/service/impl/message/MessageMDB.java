package hu.schonherz.jee.service.impl.message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

import hu.schonherz.jee.service.client.api.service.message.MessageServiceRemote;
import hu.schonherz.jee.service.client.api.vo.MessageVo;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/MessageQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MessageMDB implements MessageListener {
	@EJB
	MessageServiceRemote messageServiceRemote;

	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage) message;
		try {
			MessageVo messageVo = (MessageVo) objectMessage.getObject();

			if (messageVo.getContent().indexOf("bomb") == -1) {
				messageServiceRemote.saveMessage(messageVo);
			} else {
				MessageVo messageVo2 = new MessageVo();
				messageVo2.setFromUser(messageVo.getToUser());
				messageVo2.setToUser(messageVo.getFromUser());
				messageVo2.setContent("Terorist!!!!!");
				messageServiceRemote.saveMessage(messageVo2);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
