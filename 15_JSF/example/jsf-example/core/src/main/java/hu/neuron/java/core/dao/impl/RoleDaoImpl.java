package hu.neuron.java.core.dao.impl;

import hu.neuron.java.core.dao.RoleDao;
import hu.neuron.java.core.dao.RoleDaoCustom;
import hu.neuron.java.core.dao.UserDao;
import hu.neuron.java.core.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

public class RoleDaoImpl implements RoleDaoCustom {
	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void removeRoleFromUser(Long roleId, Long userId) throws Exception {
		User user = userDao.findOne(userId);
		user.getRoles().remove(roleDao.findOne(roleId));
		userDao.save(user);

	}

}
