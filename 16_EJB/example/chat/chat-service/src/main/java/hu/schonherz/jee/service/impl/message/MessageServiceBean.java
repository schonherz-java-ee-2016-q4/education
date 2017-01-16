package hu.schonherz.jee.service.impl.message;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.jee.core.dao.MessageDao;
import hu.schonherz.jee.core.dao.UserDao;
import hu.schonherz.jee.core.entity.Message;
import hu.schonherz.jee.core.entity.User;
import hu.schonherz.jee.service.client.api.service.message.MessageServiceLocal;
import hu.schonherz.jee.service.client.api.service.message.MessageServiceRemote;
import hu.schonherz.jee.service.client.api.vo.MessageVo;
import hu.schonherz.jee.service.client.api.vo.UserVo;
import hu.schonherz.jee.service.mapper.message.MessageVoMapper;
import hu.schonherz.jee.service.mapper.user.UserVoMapper;

@Stateless(mappedName = "MessageService")
@Remote(MessageServiceRemote.class)
@Local(MessageServiceLocal.class)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MessageServiceBean implements MessageServiceRemote, MessageServiceLocal {

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private UserDao userDao;

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "java:/jms/queue/MessageQueue")
	private Queue queue;

	@Override
	public void sendMessage(MessageVo message) {
			try {
			sendQueue(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendQueue(MessageVo message) throws Exception {

		Connection connection = null;
		MessageProducer producer = null;
		Session session = null;
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			producer = session.createProducer(queue);

			ObjectMessage messageOut = session.createObjectMessage();

			messageOut.setObject(message);

			producer.send(messageOut);
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				session.close();
			} catch (JMSException e1) {
			}
			try {
				producer.close();
			} catch (JMSException e2) {
			}
			try {
				connection.close();
			} catch (JMSException e3) {
			}
		}

	}

	@Override
	public List<MessageVo> getMessages(UserVo from, UserVo to) {
		User fromEntity = UserVoMapper.toEntity(from);
		User toEntity = UserVoMapper.toEntity(to);

		List<Message> messages = messageDao.findMessage(fromEntity, toEntity);

		return MessageVoMapper.toVo(messages);
	}

	@Override
	public void saveMessage(MessageVo message) {
		 messageDao.save(MessageVoMapper.toEntity(message));
		
		
	}

}
