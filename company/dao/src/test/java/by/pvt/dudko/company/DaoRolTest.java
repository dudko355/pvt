package by.pvt.dudko.company;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.pvt.dudko.company.dao.IDao;
import by.pvt.dudko.company.dao.impl.RoleDao;
import by.pvt.dudko.company.entities.Role;

@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class DaoRolTest {
	@Autowired
	@Qualifier("roleDao")
	private IDao<Role> rolDao;
	private static Logger log = Logger.getLogger(DaoRolTest.class);
	
	
	@Before
	public void init() {
		
	}

	@After
	public void destroy() {
	
	}

	@Test
	public void testGetRolDao() {
		
		try {
			log.info("test get rol begin");
			Assert.assertEquals(true,rolDao.get(2).getIdRole()==2);
		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get rol " + e);
		}
	}
	@Test
	public void testCreateRolDao() {
		Role rol=new Role();
		rol.setRole("user");
		try {
			int i=rolDao.getAll().size();
			log.info("test create rol begin");
			rolDao.create(rol);
			int j=rolDao.getAll().size();
			Assert.assertEquals(true,j==i+1);
		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test create rol ",e);
		}
	}
	@Test
	public void testUpdateRolDao() {
		try {
			Role rol=rolDao.get(2);
			rol.setRole("us");
			log.info("test update rol begin");
			rolDao.update(rol);
			Assert.assertEquals(true,rolDao.get(2).getRole().equals("us"));
		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test update rol " + e);
		}
	}	
	@Test
	public void testDeleteRolDao() {
		
		try {
			log.info("test delete rol begin");
		   	rolDao.delete(1);
			Assert.assertEquals(true,rolDao.get(1)==null);
		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test delete rol " + e);
		}
	}
	@Test
	public void testGetAllRolDao() {
		List<Role> rol=null;
		try {
		    rol=rolDao.getAll();
			log.info("test get all rol begin");
			Assert.assertEquals(true,rol.size()!=0);
		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get all rol " + e);
		}
	}
}
