package hu.schonherz.jee;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class StratUp {
	@EJB
	TestLocal local;

	@PostConstruct
	private void init() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 20; i++) {
			Runnable worker = new Runnable() {
				@Override
				public void run() {
					local.hello();

				}
			};
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
	}
}
