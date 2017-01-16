package hu.schonherz.jee.service.client.api.vo;

import java.io.Serializable;

public class MessageVo extends BaseVo implements Serializable {

	private static final long serialVersionUID = -5395968745834185014L;
	
	private String content;

	private UserVo fromUser;

	private UserVo toUser;

	public MessageVo() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserVo getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserVo fromUser) {
		this.fromUser = fromUser;
	}

	public UserVo getToUser() {
		return toUser;
	}

	public void setToUser(UserVo toUser) {
		this.toUser = toUser;
	}

}
