package hu.schonherz.blog.service.api;

import java.util.List;

public interface UserService {

	public List<User> findAllUser();

	User findUserByName(String name);
}
