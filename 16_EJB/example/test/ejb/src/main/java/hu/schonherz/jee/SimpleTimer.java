package hu.schonherz.jee;

import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Singleton;

@Singleton
public class SimpleTimer {

	@Schedules({ @Schedule(hour = "*", minute = "*", second = "10"),
			@Schedule(hour = "*", minute = "*", second = "45") })
	public void printTime() {
		System.out.println(getClass().getName() + ": " + new Date());
	}
}
