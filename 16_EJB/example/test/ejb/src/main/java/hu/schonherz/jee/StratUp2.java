package hu.schonherz.jee;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
@DependsOn("StratUp")
public class StratUp2 {
	@EJB
	SingletonBean bean;

	@PostConstruct
	private void init() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 20; i++) {
			Runnable worker = new Runnable() {
				@Override
				public void run() {
					bean.hello();

				}
			};
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
	}
}
