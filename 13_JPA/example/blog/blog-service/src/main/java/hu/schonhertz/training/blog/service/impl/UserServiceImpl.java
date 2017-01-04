package hu.schonhertz.training.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.dao.RoleDao;
import hu.schonhertz.training.blog.dao.UserDao;
import hu.schonhertz.training.blog.dto.RoleDto;
import hu.schonhertz.training.blog.service.UserService;
import hu.schonhertz.training.blog.service.mapper.RoleMapper;
import hu.schonhertz.training.blog.service.mapper.UserMapper;
import hu.schonhertz.training.blog.vo.UserVo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	public UserServiceImpl() {
	}

	@Override
	public UserVo findUserByName(String name) throws Exception {
		UserVo vo = UserMapper.toVo(userDao.findUserByName(name));
		return vo;

	}

	@Override
	public UserVo setUpRoles(UserVo vo) throws Exception {
		List<RoleDto> roles;
		try {
			roles = roleDao.findRolesByUserId(vo.getId());
			vo.setRoles(RoleMapper.toVo(roles));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;

	}

	@Override
	public void registrationUser(UserVo userVo) throws Exception {

		Long userId = userDao.save(UserMapper.toDto(userVo));
		RoleDto userRole = roleDao.findRoleByName("ROLE_USER");
		roleDao.addRoleToUser(userRole.getId(), userId);
	}

}
