package hu.neuron.java.web.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.neuron.java.service.queue.MessageReciverSyncRemote;
import hu.neuron.java.service.queue.MessageSenderRemote;
import hu.neuron.java.service.queue.MessageSenderSyncRemote;
import hu.neuron.java.service.queue.MessageType;

@ManagedBean(name = "queueTestBean")
@ViewScoped
public class QueueTestBean {

	@EJB
	MessageSenderSyncRemote messageSenderSyncRemote;

	@EJB
	MessageSenderRemote messageSenderRemote;

	@EJB
	MessageReciverSyncRemote messageReciverSyncRemote;

	private String message;

	private String responseMessage;

	public void sendMessageTypeA() {
		try {
			messageSenderRemote.send(message, MessageType.A);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessageTypeB() {
		try {
			messageSenderRemote.send(message, MessageType.B);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessageSync() {
		try {
			messageSenderSyncRemote.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Integer getMessageCount() {
		try {
			return messageReciverSyncRemote.browse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void read() {
		try {
			responseMessage = messageReciverSyncRemote.consum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public MessageSenderSyncRemote getMessageSenderSyncRemote() {
		return messageSenderSyncRemote;
	}

	public void setMessageSenderSyncRemote(MessageSenderSyncRemote messageSenderSyncRemote) {
		this.messageSenderSyncRemote = messageSenderSyncRemote;
	}

	public MessageSenderRemote getMessageSenderRemote() {
		return messageSenderRemote;
	}

	public void setMessageSenderRemote(MessageSenderRemote messageSenderRemote) {
		this.messageSenderRemote = messageSenderRemote;
	}

	public MessageReciverSyncRemote getMessageReciverSyncRemote() {
		return messageReciverSyncRemote;
	}

	public void setMessageReciverSyncRemote(MessageReciverSyncRemote messageReciverSyncRemote) {
		this.messageReciverSyncRemote = messageReciverSyncRemote;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
}
