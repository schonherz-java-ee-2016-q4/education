package hu.neuron.java.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import hu.neuron.java.core.dao.RoleDao;
import hu.neuron.java.core.dao.UserDao;
import hu.neuron.java.core.entity.Role;

@Component
@Transactional
public class CreateShema {
	private static final Logger logger = LogManager.getLogger(CreateShema.class);

	@Autowired
	public RoleDao roleDAO;

	@Autowired
	public UserDao userDao;

	@Autowired
	DataGenerator dataGenerator;

	public void insertRoles() {
		Role dto = null;
		try {
			if (roleDAO.findRoleByName("ROLE_USER") == null) {
				dto = new Role();
				dto.setName("ROLE_USER");
				roleDAO.save(dto);
			}
			if (roleDAO.findRoleByName("ROLE_ADMIN") == null) {
				dto = new Role();
				dto.setName("ROLE_ADMIN");
				roleDAO.save(dto);
			}

			if (userDao.count() == 0) {
				dataGenerator.generateUsers();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
