package hu.schonherz.jee.service.impl.user;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.jee.core.dao.RoleDao;
import hu.schonherz.jee.core.dao.UserDao;
import hu.schonherz.jee.core.entity.Role;
import hu.schonherz.jee.core.entity.User;
import hu.schonherz.jee.service.client.api.service.role.RoleServiceRemote;
import hu.schonherz.jee.service.client.api.service.user.UserServiceLocal;
import hu.schonherz.jee.service.client.api.service.user.UserServiceRemote;
import hu.schonherz.jee.service.client.api.vo.RoleVo;
import hu.schonherz.jee.service.client.api.vo.UserVo;
import hu.schonherz.jee.service.mapper.role.RoleVoMapper;
import hu.schonherz.jee.service.mapper.user.UserVoMapper;

@Stateless(mappedName = "UserService")
@Remote(UserServiceRemote.class)
@Local(UserServiceLocal.class)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserServiceBean implements UserServiceRemote, UserServiceLocal {

	private static final String ROLE_USER = "ROLE_USER";

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Override
	public UserVo findByUsername(String username) {
		User user = userDao.findByUsername(username);
		return UserVoMapper.toVo(user);

	}

	@Override
	public UserVo registrationUser(UserVo userVo) {
		User user = UserVoMapper.toEntity(userVo);
		
		List<Role> roles = new ArrayList<>();

		Role role = roleDao.findByName(ROLE_USER);
		roles.add(role);
		user.setRoles(roles);

		user = userDao.save(user);
		return UserVoMapper.toVo(user);
	}

}
