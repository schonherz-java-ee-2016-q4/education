package hu.neuron.java.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.neuron.java.core.dao.RoleDao;
import hu.neuron.java.core.dao.UserDao;
import hu.neuron.java.core.entity.Gender;
import hu.neuron.java.core.entity.Role;
import hu.neuron.java.core.entity.User;
import hu.neuron.java.service.UserService;
import hu.neuron.java.service.converter.GenericConverter;
import hu.neuron.java.service.exception.ServiceException;
import hu.neuron.java.service.vo.UserVO;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 7081140322696230547L;

	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	private GenericConverter<User, UserVO> userConverter = new GenericConverter<>(User.class, UserVO.class);

	public UserServiceImpl() {
	}

	@Override
	public UserVO findUserByName(String name) throws ServiceException {
		try {
			UserVO vo = userConverter.toVO(userDao.findUserByName(name));
			return vo;
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void registrationUser(UserVO userVO) throws ServiceException {
		try {
			User user = userDao.save(userConverter.toEntity(userVO));
			Role userRole = roleDao.findRoleByName("ROLE_USER");
			roleDao.addRoleToUser(userRole.getId(), user.getId());
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<UserVO> getUserList(int page, int size, String sortField, int sortOrder, String filter,
			String filterColumnName) throws ServiceException {
		try {
			Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
			PageRequest pageRequest = new PageRequest(page, size,
					new Sort(new org.springframework.data.domain.Sort.Order(dir, sortField)));
			List<UserVO> ret = new ArrayList<UserVO>(size);
			Page<User> entities;

			if (filter.length() != 0 && filterColumnName.equals("firstName")) {
				entities = userDao.findByFirstNameOrLastNameStartingWith(filter, pageRequest);
			} else if  (filter.length() != 0 && filterColumnName.equals("userName")) {
				entities = userDao.findByUserNameStartsWith(filter, pageRequest);
			} else if (filter.length() != 0 && filterColumnName.equals("gender")) {
				entities = userDao.findByGender(Gender.valueOf(filter), pageRequest);
			} else if (filter.length() != 0 && filterColumnName.equals("email")) {
				entities = userDao.findByEmailStartsWith(filter, pageRequest);
			} else {
				entities = userDao.findAll(pageRequest);
			}
			if (entities != null && entities.getSize() > 0) {
				List<User> contents = entities.getContent();
				for (User m : contents) {
					ret.add(userConverter.toVO(m));

				}
			}
			return ret;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Long getUserCount() throws ServiceException {
		try {
			return userDao.count();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public UserVO findUserById(Long id) throws ServiceException {
		try {
			return userConverter.toVO(userDao.findOne(id));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveUser(UserVO userVO) throws ServiceException {
		try {
			userDao.save(userConverter.toEntity(userVO));
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public Set<UserVO> getAllUser() {
		return userConverter.toVO(userDao.findAll());
	}

}
