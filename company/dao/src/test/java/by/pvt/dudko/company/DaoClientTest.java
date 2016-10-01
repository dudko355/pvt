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

import by.pvt.dudko.company.dao.IClientDao;
import by.pvt.dudko.company.dao.IDao;
import by.pvt.dudko.company.dao.impl.ClientDao;
import by.pvt.dudko.company.dao.impl.RoleDao;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Role;

@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class DaoClientTest {
	@Autowired
	private IClientDao clientDao;
	@Autowired
	@Qualifier("roleDao")
	private IDao<Role> rolDao;
	private static Logger log = Logger.getLogger(DaoClientTest.class);
	

	@Before
	public void init() {
		
	}

	@After
	public void destroy() {
	
	}

	@Test
	public void testGetClientDao() {

		try {
			log.info("test get client begin");
			Assert.assertEquals(true,  clientDao.get(1).getRole().getIdRole() == 1);
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test get client " + e);
		}
	}

	@Test
	public void testCreateClientDao() {

		try {
			int i=clientDao.getAll().size();
			Client client = new Client();
			client.setLogin("lex");
			client.setPassword("111");
			client.setRole((Role)rolDao.get(2));
			log.info("test create client begin");
			clientDao.create(client);
			int j=clientDao.getAll().size();
			Assert.assertEquals(true, j==i+1);
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test create client " + e);
		}
	}

	@Test
	public void testUpdateClientDao() {
		Client client = null;

		try {
			client = clientDao.get(2);
			client.setLogin("lex");
			log.info("test update client begin");
			clientDao.update(client);
			Assert.assertEquals(true, clientDao.get(2).getLogin().equals("lex"));
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test update client " + e);
		}
	}

	@Test
	public void testDeleteClientDao() {

		try {

			clientDao.delete(3);
			log.info("test delete client begin");
			Assert.assertEquals(true, clientDao.get(3) == null);

		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test delete client " + e);
		}
	}

	@Test
	public void testGetAllClientDao() {
		List<Client> clients = null;
		try {
			clients = clientDao.getAll();
			log.info("test get all client begin");
			Assert.assertEquals(true, clients != null);

		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test get all client " + e);
		}
	}

	@Test
	public void testGetInitClientDao() {
		Client clients = null;
		String login = "root";
		String pass = "222222";
		try {
			clients = clientDao.getClientByLoginName(login, pass);
			log.info("test get init client begin");
			Assert.assertEquals(true, clients.getIdClient() == 3);
		} catch (Exception e) {
			
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test get init client " + e);
		}
	}

	
}
