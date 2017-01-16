package hu.schonherz.jee.service.mapper.message;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.schonherz.jee.core.entity.Message;
import hu.schonherz.jee.service.client.api.vo.MessageVo;

public class MessageVoMapper {
	static Mapper mapper = new DozerBeanMapper();

	public static MessageVo toVo(Message message) {
		if (message == null) {
			return null;
		}
		return mapper.map(message, MessageVo.class);
	}

	public static Message toEntity(MessageVo messageVo) {
		if (messageVo == null) {
			return null;
		}
		return mapper.map(messageVo, Message.class);
	}

	public static List<MessageVo> toVo(List<Message> message) {
		List<MessageVo> rv = new ArrayList<>();
		for (Message messages : message) {
			rv.add(toVo(messages));
		}
		return rv;
	}

	public static List<Message> toEntity(List<MessageVo> message) {
		List<Message> rv = new ArrayList<>();
		for (MessageVo messages : message) {
			rv.add(toEntity(messages));
		}
		return rv;
	}
}
