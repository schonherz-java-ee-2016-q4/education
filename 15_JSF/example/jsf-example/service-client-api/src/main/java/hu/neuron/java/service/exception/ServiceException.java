package hu.neuron.java.service.exception;

public class ServiceException extends Exception {

	public ServiceException(Exception e) {
		super(e);
	}

	private static final long serialVersionUID = 1L;

}
