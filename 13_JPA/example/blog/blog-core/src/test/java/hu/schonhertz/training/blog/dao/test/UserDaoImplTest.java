package hu.schonhertz.training.blog.dao.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.dao.UserDao;
import hu.schonhertz.training.blog.dto.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-core-test.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@Rollback(false)
public class UserDaoImplTest {

	private static final Logger logger = Logger.getLogger(UserDaoImplTest.class);

	private static UserDto dto;

	@Autowired
	UserDao dao;

	@Test
	public void test1Save() {
		try {
			dto = new UserDto(null, "A", "B");

			Long id = dao.save(dto);
			logger.debug("id: " + id);

			dto.setId(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2Update() {
		try {
			dto.setUserName("C");
			dao.update(dto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5Delete() {
		try {
			dao.delete(dto.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3Find() {
		try {
			UserDto rvDto = dao.find(dto.getId());
			logger.debug("rv: " + rvDto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4Find() {
		try {
			UserDto rvDto = dao.findUserByName(dto.getUserName());
			logger.debug("rv: " + rvDto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5FindAll() {
		try {
			List<UserDto> dtos = dao.findAll();
			for (UserDto userDto : dtos) {
				logger.debug("rv: " + userDto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
