package hu.schonherz.jee;

import java.util.Date;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Local(TestLocal.class)
@Remote(TestRemote.class)
public class TestBean implements TestLocal, TestRemote {

	@Override
	public void hello() {
		System.out.println(this);
	}

	@Override
	@Asynchronous
	public Future<Date> asynchronousPrintTime() {
		System.out.println("call printTime" + new Date());
		try {
			Thread.sleep(5 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AsyncResult<Date>(new Date());
	}

}
