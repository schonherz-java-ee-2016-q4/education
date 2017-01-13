package hu.neuron.java.service;

import java.util.List;

import hu.neuron.java.service.vo.UserVO;

public interface UserServiceRemote {

	public UserVO findUserByName(String name) throws Exception;

	public UserVO registrationUser(UserVO userVO) throws Exception;

	public List<UserVO> getUserList(int i, int pageSize, String sortField, int dir, String filter,
			String filterColumnName);

	public List<UserVO> getUsers();
	
	public Integer getUserCount();

	public UserVO saveUser(UserVO selectedUser);

	public UserVO findById(Long id);
	
	public void generate();


}
