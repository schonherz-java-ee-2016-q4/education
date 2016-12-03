package hu.schonherz.blog.service;

import java.util.List;

import org.junit.Test;

import hu.schonherz.blog.service.api.User;

public class UserServiceImplTest {

	@Test
	public void testFindAllUser() {
		UserServiceImpl serviceImpl = new UserServiceImpl();
		List<User> list = serviceImpl.findAllUser();

		for (User user : list) {
			System.out.println(user.getName().getFirst());
		}

	}

}
