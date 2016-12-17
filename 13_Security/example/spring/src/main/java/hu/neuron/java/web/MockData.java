package hu.neuron.java.web;

import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.neuron.java.web.vo.RoleVO;
import hu.neuron.java.web.vo.UserVO;

public class MockData {

	public static UserVO findUserAndRolesByName(String username) {

		ArrayList<RoleVO> roles = new ArrayList<>();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String password = null;

		if (username.equals("user")) {
			password = bCryptPasswordEncoder.encode("user");
			roles.add(new RoleVO("ROLE_USER"));
		} else if (username.equals("manager")) {
			password = bCryptPasswordEncoder.encode("manager");
			roles.add(new RoleVO("ROLE_USER"));
			roles.add(new RoleVO("ROLE_MANAGER"));
		} else if (username.equals("admin")) {
			password = bCryptPasswordEncoder.encode("admin");
			roles.add(new RoleVO("ROLE_USER"));
			roles.add(new RoleVO("ROLE_ADMIN"));
			roles.add(new RoleVO("ROLE_MANAGER"));
		} else {
			return null;
		}

		UserVO userVO = new UserVO();
		userVO.setUserName(username);
		userVO.setPassword(password);
		userVO.setRoles(roles);

		return userVO;
	}

}
