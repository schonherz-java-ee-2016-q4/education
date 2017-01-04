package hu.schonhertz.training.blog.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.dao.RoleDao;
import hu.schonhertz.training.blog.dao.UserDao;
import hu.schonhertz.training.blog.dto.RoleDto;
import hu.schonhertz.training.blog.dto.UserDto;
import hu.schonhertz.training.blog.entity.Role;
import hu.schonhertz.training.blog.entity.User;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class RoleDaoImpl extends BaseDaoImpl<Role, RoleDto> implements BaseConvertDao<Role, RoleDto>, RoleDao {
	@Autowired
	UserDao userDAO;

	@Override
	public Role toEntity(RoleDto dto, Role entity) {
		Role ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new Role();

		}
		ret.setId(dto.getId());
		ret.setName(dto.getName());
		return ret;
	}

	@Override
	public RoleDto toDto(Role entity) {
		RoleDto ret = new RoleDto();
		if (entity == null) {
			return null;
		}
		ret.setName(entity.getName());
		ret.setId(entity.getId());

		return ret;
	}

	@Override
	public List<RoleDto> findRolesByUserId(Long id) throws Exception {
		TypedQuery<Role> createNamedQuery = entityManager.createNamedQuery("findRolesByUserId", entityClass);
		createNamedQuery.setParameter("pUserId", id);
		List<Role> resultList = createNamedQuery.getResultList();

		List<RoleDto> rv = new ArrayList<RoleDto>();
		for (Role e : resultList) {
			rv.add(toDto(e));
		}
		return rv;

	}

	@Override
	public void addRoleToUser(Long roleId, Long userId) throws Exception {
		BaseConvertDao<User, UserDto> baseConvertDao = (BaseConvertDao<User, UserDto>) userDAO;
		User user = baseConvertDao.findEntity(userId);
		Collection<Role> roles = user.getRoles();
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		roles.add(findEntity(roleId));
		user.setRoles(roles);
		baseConvertDao.saveEntity(user);
	}

	@Override
	public void removeRoleFromUser(Long roleId, Long userId) throws Exception {
		BaseConvertDao<User, UserDto> baseConvertDao = (BaseConvertDao<User, UserDto>) userDAO;
		User user = baseConvertDao.findEntity(userId);
		user.getRoles().remove(findEntity(roleId));
		baseConvertDao.saveEntity(user);

	}

	@Override
	public RoleDto findRoleByName(String name) throws Exception {
		TypedQuery<Role> createNamedQuery = entityManager.createNamedQuery("findRoleByName", entityClass);
		createNamedQuery.setParameter("pName", name);

		Role rv = null;
		try {
			rv = createNamedQuery.getSingleResult();
		} catch (NoResultException e) {

		}
		return toDto(rv);
	}

}
