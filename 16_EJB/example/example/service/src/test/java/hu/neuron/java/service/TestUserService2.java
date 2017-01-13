package hu.neuron.java.service;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import hu.neuron.java.service.vo.UserVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class TestUserService2 {
	static final Logger logger = LogManager.getLogger(TestUserService2.class.getName());

	@EJB
	UserServiceLocal serviceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable e) {

			logger.error(e.getMessage(), e);
			throw e;
		}

	}

	@Test
	public void test2RegistrationClient() {
		try {
			UserVO userVO = new UserVO();
			userVO.setUsername("test2");
			serviceLocal.registrationUser(userVO);
			List<UserVO> vo = serviceLocal.getUsers();
			for (UserVO userVO2 : vo) {
				logger.debug(userVO2);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

}
