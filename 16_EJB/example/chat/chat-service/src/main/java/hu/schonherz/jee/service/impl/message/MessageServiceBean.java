package hu.schonherz.jee.service.impl.message;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.jee.core.dao.MessageDao;
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

	@Override
	public void sendMessage(MessageVo message) {
		messageDao.save(MessageVoMapper.toEntity(message));
	}

	@Override
	public List<MessageVo> getMessages(UserVo from, UserVo to) {
		User fromEntity = UserVoMapper.toEntity(from);
		User toEntity = UserVoMapper.toEntity(to);

		List<Message> messages = messageDao.findByFromUserAndToUserOrFromUserAndToUserOrderByrecDateDesc(fromEntity,
				toEntity, toEntity, fromEntity);

		return MessageVoMapper.toVo(messages);
	}

}
