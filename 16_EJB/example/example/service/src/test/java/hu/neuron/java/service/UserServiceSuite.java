package hu.neuron.java.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateContext.class, TestUserService.class, TestUserService2.class, CloseContext.class })
public class UserServiceSuite {
	// nothing
}