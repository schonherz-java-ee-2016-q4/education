package hu.schonherz.jee.service.client.api.service.message;

import java.util.List;

import hu.schonherz.jee.service.client.api.vo.MessageVo;
import hu.schonherz.jee.service.client.api.vo.UserVo;

public interface MessageService {

	void sendMessage(MessageVo message);
	
	void saveMessage(MessageVo message);

	List<MessageVo> getMessages(UserVo from, UserVo to);

}
