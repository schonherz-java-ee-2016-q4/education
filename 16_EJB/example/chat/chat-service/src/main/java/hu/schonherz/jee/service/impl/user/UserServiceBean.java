package hu.schonherz.jee.service.impl.user;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.jee.core.dao.UserDao;
import hu.schonherz.jee.core.entity.User;
import hu.schonherz.jee.service.client.api.service.user.UserServiceLocal;
import hu.schonherz.jee.service.client.api.service.user.UserServiceRemote;
import hu.schonherz.jee.service.client.api.vo.UserVo;

@Stateless(mappedName = "UserService")
@Remote(UserServiceRemote.class)
@Local(UserServiceLocal.class)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class UserServiceBean implements UserServiceRemote, UserServiceLocal {

	@Autowired
	UserDao userDao;

	@Override
	public UserVo findByUsername(String username) {
		User user = userDao.findByUsername(username);
		return null;

	}

}
