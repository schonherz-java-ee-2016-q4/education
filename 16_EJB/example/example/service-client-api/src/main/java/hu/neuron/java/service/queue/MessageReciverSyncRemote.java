package hu.neuron.java.service.queue;

public interface MessageReciverSyncRemote {

	String consum() throws Exception;

	Integer browse() throws Exception;
}
