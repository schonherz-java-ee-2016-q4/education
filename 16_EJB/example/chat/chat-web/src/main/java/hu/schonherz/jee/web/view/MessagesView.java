package hu.schonherz.jee.web.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.jee.service.client.api.service.message.MessageService;
import hu.schonherz.jee.service.client.api.service.message.MessageServiceRemote;
import hu.schonherz.jee.service.client.api.service.user.UserServiceRemote;
import hu.schonherz.jee.service.client.api.vo.MessageVo;
import hu.schonherz.jee.service.client.api.vo.UserVo;

@ManagedBean(name = "messagesView")
@ViewScoped
public class MessagesView {

	@EJB
	private UserServiceRemote userServiceRemote;

	@EJB
	private MessageServiceRemote messageServiceRemote;

	private UserVo fromUser;
	private UserVo toUser;
	private String content;

	private List<UserVo> users;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		String actUser = request.getUserPrincipal().getName();
		fromUser = userServiceRemote.findByUsername(actUser);
		List<UserVo> findAll = userServiceRemote.findAll();
		findAll.remove(fromUser);
		users = findAll;
	}

	public void send() {
		MessageVo message = new MessageVo();
		message.setContent(content);
		message.setFromUser(fromUser);
		message.setToUser(toUser);

		messageServiceRemote.sendMessage(message);
	}

	public List<MessageVo> getMessages() {
		if (toUser != null) {
			return messageServiceRemote.getMessages(fromUser, toUser);
		}
		return new ArrayList<MessageVo>();
	}

	public UserVo getToUser() {
		return toUser;
	}

	public void setToUser(UserVo toUser) {
		this.toUser = toUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserServiceRemote getUserServiceRemote() {
		return userServiceRemote;
	}

	public void setUserServiceRemote(UserServiceRemote userServiceRemote) {
		this.userServiceRemote = userServiceRemote;
	}

	public MessageServiceRemote getMessageServiceRemote() {
		return messageServiceRemote;
	}

	public void setMessageServiceRemote(MessageServiceRemote messageServiceRemote) {
		this.messageServiceRemote = messageServiceRemote;
	}

	public UserVo getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserVo fromUser) {
		this.fromUser = fromUser;
	}

	public List<UserVo> getUsers() {
		return users;
	}

	public void setUsers(List<UserVo> users) {
		this.users = users;
	}

}
