package hu.schonherz.training.ws.nonwebapp;

import javax.jws.WebService;

@WebService(endpointInterface = "hu.schonherz.training.ws.nonwebapp.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String helloWorld(String name) {
		return "Hello World JAX-WS via Non-Webapp by " + name;
	}

}
