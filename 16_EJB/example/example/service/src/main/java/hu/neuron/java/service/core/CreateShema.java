package hu.neuron.java.service.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.java.dao.RoleDao;
import hu.schonherz.java.dao.UserDao;
import hu.schonherz.java.entities.Role;

@Component
@Transactional(propagation = Propagation.REQUIRED)
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
			if (roleDAO.findByName("ROLE_USER") == null) {
				dto = new Role();
				dto.setName("ROLE_USER");
				roleDAO.save(dto);
			}
			if (roleDAO.findByName("ROLE_ADMIN") == null) {
				dto = new Role();
				dto.setName("ROLE_ADMIN");
				roleDAO.save(dto);
			}

		 
				dataGenerator.generateUsers();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
