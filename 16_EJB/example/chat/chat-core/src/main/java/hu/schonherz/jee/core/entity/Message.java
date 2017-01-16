package hu.schonherz.jee.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Message extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Lob
	private String content;

	@ManyToOne
	private User fromUser;

	@ManyToOne
	private User toUser;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

}
