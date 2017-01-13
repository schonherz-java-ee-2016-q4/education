package hu.neuron.java.service.queue;

import hu.neuron.java.service.queue.vo.BadWordMessageRequest;
import hu.neuron.java.service.queue.vo.BadWordMessageResponse;

public interface BadWordSenderRemote {

	public void sendRequest(BadWordMessageRequest message) throws Exception;

	public void sendResponse(BadWordMessageResponse message) throws Exception;
}
