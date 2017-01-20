package hu.schonherz.training.ws.nonwebapp;

import javax.xml.ws.Endpoint;

public class HelloWorldPublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8085/WS/HelloWorld", new HelloWorldImpl());
	}
}
