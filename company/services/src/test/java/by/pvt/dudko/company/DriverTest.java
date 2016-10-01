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

import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Driver;
import by.pvt.dudko.company.implement.IDriverService;
import by.pvt.dudko.company.service.ClientServiceImpl;
import by.pvt.dudko.company.service.DriverServerImpl;

@ContextConfiguration("/testContextServices.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class DriverTest {
	@Autowired
	private IDriverService driverServerImpl;
	private static Logger log = Logger.getLogger(DriverTest.class);
	
	
	@Test
	public void testGetAllDriver() {

		try {
			log.info("test get all driver in service begin");
			List<Driver> allDriver=driverServerImpl.allDriver();
			Assert.assertEquals(true, allDriver.size() !=0);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get all driver in service" + e);
		}
	}
}
