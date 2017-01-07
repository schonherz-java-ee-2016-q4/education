package hu.schonherz.jee;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
@DependsOn("StratUp2")
public class StratUp3 {
	@EJB
	TestLocal local;

	@PostConstruct
	private void init() {
		Future<Date> future = local.asynchronousPrintTime();
		System.out.println("Running");
		while (!future.isDone()) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

		}
	}
}
