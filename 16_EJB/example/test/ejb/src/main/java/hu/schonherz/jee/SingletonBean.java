package hu.schonherz.jee;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

/**
 * Session Bean implementation class Singleton
 */
@Singleton(mappedName = "SingletonNewName")
@LocalBean
@Interceptors(SimpleInterceptor.class)
public class SingletonBean {

	public void hello() {
		System.out.println(this);
	}

}
