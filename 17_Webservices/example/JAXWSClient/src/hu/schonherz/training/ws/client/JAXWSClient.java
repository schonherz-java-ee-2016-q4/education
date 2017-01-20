package hu.schonherz.training.ws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import hu.schonherz.training.ws.nonwebapp.HelloWorldImplService;

public class JAXWSClient {

	public static void main(String[] args) throws MalformedURLException {

		// Non-Webapp Service
		HelloWorldImplService serviceNonWebapp = new HelloWorldImplService();
		hu.schonherz.training.ws.nonwebapp.HelloWorld helloWorld = serviceNonWebapp.getHelloWorldImplPort();
		System.out.println(helloWorld.helloWorld("Jani"));
		
		// Webapp Service
		URL url = new URL("http://localhost:8080/WebServicesExample/hello?wsdl");
		QName qname = new QName("http://webapp.ws.training.schonherz.hu/", "HelloWorldService");

		Service service = Service.create(url, qname);

		hu.schonherz.training.ws.webapp.HelloWorld hello = service
				.getPort(hu.schonherz.training.ws.webapp.HelloWorld.class);

		System.out.println(hello.getHelloWorld("Jani"));
	}
}
