package hu.schonhertz.training.blog.dao;

import hu.schonhertz.training.blog.dto.UserDto;

public interface UserDao extends BaseDao<UserDto> {

	UserDto findUserByName(String name) throws Exception;

}
