package hu.schonhertz.training.blog.repository.imp;

import org.springframework.beans.factory.annotation.Autowired;

import hu.schonhertz.training.blog.entity.User;
import hu.schonhertz.training.blog.repository.RoleRepository;
import hu.schonhertz.training.blog.repository.RoleRepositoryCustom;
import hu.schonhertz.training.blog.repository.UserRepository;

public class RoleRepositoryImpl implements RoleRepositoryCustom {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void removeRoleFromUser(Long roleId, Long userId) throws Exception {
		User user = userRepository.findOne(userId);
		user.getRoles().remove(roleRepository.findOne(roleId));
		userRepository.save(user);
	}

}
