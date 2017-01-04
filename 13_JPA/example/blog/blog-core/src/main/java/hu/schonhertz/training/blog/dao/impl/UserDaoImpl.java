package hu.schonhertz.training.blog.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.dao.UserDao;
import hu.schonhertz.training.blog.dto.UserDto;
import hu.schonhertz.training.blog.entity.User;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public class UserDaoImpl extends BaseDaoImpl<User, UserDto> implements BaseConvertDao<User, UserDto>, UserDao {

	@Override
	public User toEntity(UserDto dto, User entity) {
		User ret = entity;
		if (dto.getId() == null || entity == null) {
			ret = new User();
			ret.setId(dto.getId());

		}
		ret.setId(dto.getId());
		ret.setUserName(dto.getUserName());
		ret.setPassword(dto.getPassword());

		return ret;
	}

	@Override
	public UserDto toDto(User entity) {
		UserDto ret = new UserDto();
		if (entity == null) {
			return null;
		}
		ret.setId(entity.getId());
		ret.setUserName(entity.getUserName());
		ret.setPassword(entity.getPassword());

		return ret;
	}

	@Override
	public UserDto findUserByName(String name) throws Exception {
		User rv;
		try {
			Query query = entityManager.createNamedQuery("findUserByName", entityClass);
			query.setParameter("pName", name);

			rv = (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return toDto(rv);
	}

}
