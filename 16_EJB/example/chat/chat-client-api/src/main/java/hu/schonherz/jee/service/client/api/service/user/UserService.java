package hu.schonherz.jee.service.client.api.service.user;

import hu.schonherz.jee.service.client.api.vo.UserVo;

public interface UserService {

	UserVo findByUsername(String username);
}
