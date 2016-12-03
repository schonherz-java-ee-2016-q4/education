package hu.schonherz.blog.service;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;

import hu.schonherz.blog.service.api.User;
import hu.schonherz.blog.service.api.UserResult;
import hu.schonherz.blog.service.api.UserService;

public class UserServiceImpl implements UserService {

	private UserResult result;

	public UserServiceImpl() {
		result = init();
	}

	@Override
	public List<User> findAllUser() {
		return result.getResults();
	}

	private UserResult init() {
		Gson gson = new Gson();

		try {

			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("example.txt").getFile());

			result = gson.fromJson(new FileReader(file), UserResult.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param name
	 * @return
	 */
	public User findUserByName(String name) {
		User ret = null;
		List<User> users = result.getResults();
		for (User user : users) {
			if (user.getLogin().getUsername().equals(name)) {
				ret = user;
				break;
			}
		}
		return ret;
	}
}
