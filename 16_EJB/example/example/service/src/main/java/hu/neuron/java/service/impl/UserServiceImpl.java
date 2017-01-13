package hu.neuron.java.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.neuron.java.service.UserConverter;
import hu.neuron.java.service.UserServiceLocal;
import hu.neuron.java.service.UserServiceRemote;
import hu.neuron.java.service.core.CreateShema;
import hu.neuron.java.service.vo.UserVO;
import hu.schonherz.java.dao.RoleDao;
import hu.schonherz.java.dao.UserDao;
import hu.schonherz.java.entities.Role;
import hu.schonherz.java.entities.User;

@Stateless(mappedName = "UserService", name = "UserService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(UserServiceLocal.class)
@Remote(UserServiceRemote.class)
public class UserServiceImpl implements UserServiceLocal, UserServiceRemote {

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	CreateShema createShema;

	@Override
	public UserVO findUserByName(String name) throws Exception {
		User user = userDao.findByUsername(name);
		return UserConverter.toVo(user);
	}

	@Override
	public UserVO registrationUser(UserVO userVO) throws Exception {

		User user = UserConverter.toEntity(userVO);

		List<Role> roles = new ArrayList<>();

		Role role = getUserRole();

		roles.add(role);

		user.setRoles(roles);

		user = userDao.save(user);

		return UserConverter.toVo(user);

	}

	private Role getUserRole() {
		Role role = roleDao.findByName("ROLE_USER");
		if (role == null) {
			role = new Role();
			role.setName("ROLE_USER");
			role = roleDao.save(role);

		}
		return role;
	}

	@Override
	public List<UserVO> getUserList(int i, int pageSize, String sortField, int dir, String filter,
			String filterColumnName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getUserCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO saveUser(UserVO selectedUser) {
		return selectedUser;
		// TODO Auto-generated method stub

	}

	@Override
	public UserVO findById(Long id) {
		return UserConverter.toVo(userDao.findOne(id));
	}

	@Override
	public List<UserVO> getUsers() {

		return UserConverter.toVo(userDao.findAll());
	}

	@Override
	public void generate() {
		createShema.insertRoles();

	}

}
