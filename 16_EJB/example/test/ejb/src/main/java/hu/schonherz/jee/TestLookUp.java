package hu.schonherz.jee;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestLookUp {

	public static void main(String[] args) {

		Properties prop = new Properties();

		prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		prop.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
		prop.put(Context.SECURITY_PRINCIPAL, "admin");
		prop.put(Context.SECURITY_CREDENTIALS, "adminpassword1");

		prop.put("jboss.naming.client.ejb.context", true);

		try {
			Context context = new InitialContext(prop);

			TestRemote remote = (TestRemote) context
					.lookup("java:global/ear/ejb-0.0.1-SNAPSHOT/TestBean!hu.schonherz.jee.TestRemote");

			remote.hello();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
