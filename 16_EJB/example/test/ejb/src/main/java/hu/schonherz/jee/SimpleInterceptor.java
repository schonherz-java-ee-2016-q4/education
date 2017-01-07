package hu.schonherz.jee;

import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class SimpleInterceptor {

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		Date date = new Date();
		System.out.println("before " + context.getMethod().getName());

		Object result = context.proceed();

		System.out.println("after " + (new Date().getTime() - date.getTime()));

		return result;
	}

}