package hu.schonherz.jee.service.client.api.service.user;

import java.util.List;

import hu.schonherz.jee.service.client.api.vo.UserVo;

public interface UserService {

	UserVo findByUsername(String username);

	UserVo registrationUser(UserVo userVo);
	
	List<UserVo> findAll();
}
