package hu.schonhertz.training.blog.dao.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import hu.schonhertz.training.blog.entity.Role;
import hu.schonhertz.training.blog.repository.RoleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-core-test.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@Rollback(false)
public class RoleDaoTest {

	@Autowired
	RoleRepository roleDao;

	@Test
	public void test1() {
		Role role = new Role();
		role.setName("USER_ROLE");
		Role roleReturn = roleDao.save(role);
		System.out.println(roleReturn.getId());
	}

	@Test
	public void test2() {

	}

}
