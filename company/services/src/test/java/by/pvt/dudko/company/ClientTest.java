package by.pvt.dudko.company;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.implement.IClientService;
import by.pvt.dudko.company.service.CarServiceImpl;
import by.pvt.dudko.company.service.ClientServiceImpl;

@ContextConfiguration("/testContextServices.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class ClientTest {
	@Autowired 
	private IClientService clientServiceImpl;
	private static Logger log = Logger.getLogger(ClientTest.class);
	
	@Before
	public void init() {
	
	}

	@After
	public void destroy() {
	        
	}
	
	@Test
	public void testGetAllClient() {

		try {
			log.info("test get all client in service begin");
			List<Client> allClient=clientServiceImpl.allClient();
			Assert.assertEquals(true, allClient.size()!=0);
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get all client in service" + e);
		}
	}
	@Test
	public void testGetUser() {

		try {
			log.info("test get  client in service begin");
			Client client=clientServiceImpl.getUser("alex", "111111");
			Assert.assertEquals(true, client.getIdClient() == 2);
			Client client1=clientServiceImpl.getUser("alexe", "111111");
			Assert.assertEquals(true, client1 == null);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get  client in service" + e);
		}
	}
	@Test
	public void testRegistrationAndDelete() {

		try {
			log.info("test registration in service begin");
			Client client=clientServiceImpl.registration("211", "Tema");
			Assert.assertEquals(true, clientServiceImpl.allClient().size() !=0);
			Client client1=clientServiceImpl.getUser("Tema", "211");
	        clientServiceImpl.deleteUser(client);
	        Assert.assertEquals(true, clientServiceImpl.allClient().size() !=0);
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test registration in service" + e);
		}
	}
	@Test
	public void testTripClient() {

		try {
			log.info("test tripClient in service begin");
			Client client=clientServiceImpl.allClient().get(1);
			List<Trip> list=clientServiceImpl.tripsClient(client);
			Assert.assertEquals(true, list.size() !=0);
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test tripClient in service" + e);
		}
	}
	
}
