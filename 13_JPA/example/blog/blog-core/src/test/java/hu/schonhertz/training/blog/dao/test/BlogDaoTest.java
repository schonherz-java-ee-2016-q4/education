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

import hu.schonhertz.training.blog.dao.BlogDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-core-test.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@Rollback(false)
public class BlogDaoTest {
	@Autowired
	BlogDao blogDao;

	@Test
	public void test1CreateDef() {
	}

	@Test
	public void test2Create() {
	}

	@Test
	public void test2GetAll() {
	}

	@Test
	public void test2GetById() {
	}
}
