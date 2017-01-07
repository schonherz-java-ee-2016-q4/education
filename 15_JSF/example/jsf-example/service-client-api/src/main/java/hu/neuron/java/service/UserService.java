package hu.neuron.java.service;

import java.util.List;
import java.util.Set;

import hu.neuron.java.service.exception.ServiceException;
import hu.neuron.java.service.vo.UserVO;

public interface UserService {

	public UserVO findUserByName(String name) throws ServiceException;

	public UserVO findUserById(Long id) throws ServiceException;

	public void registrationUser(UserVO userVO) throws ServiceException;

	public List<UserVO> getUserList(int i, int pageSize, String sortField, int dir, String filter,
			String filterColumnName) throws ServiceException;

	public Long getUserCount() throws ServiceException;

	public void saveUser(UserVO userVO) throws ServiceException;

	public Set<UserVO> getAllUser() throws ServiceException;
}
