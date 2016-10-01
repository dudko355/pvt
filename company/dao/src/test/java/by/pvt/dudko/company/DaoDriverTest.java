package by.pvt.dudko.company;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
import by.pvt.dudko.company.dao.impl.CarDao;
import by.pvt.dudko.company.dao.impl.DriverDao;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Driver;


@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class DaoDriverTest {
	@Autowired
	@Qualifier("carDao")
	private IDao<Car> carDao;
	@Autowired
	@Qualifier("driverDao")
	private IDao<Driver> driverDao;
	private static Logger log = Logger.getLogger(DaoDriverTest.class);
	

	@Before
	public void init() {
	
	}

	@After
	public void destroy() {
		
	}
	@Test
	public void testGetDriverDao() {

		try {
			log.info("test get Driver begin");
			Assert.assertEquals(true, ((Driver) driverDao.get(3)).getIdDriver()==3);
		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get Driver ",e);
		}
	}
	@Test
	public void testCreateDriverDao() {
		try {
			
			int i=driverDao.getAll().size();
			Driver driver = new Driver("den",11);
			Car car=(Car) carDao.get(1);
			Set cars=(Set<Car>) driver.getCars();
			cars.add(car);
			driver.setCars(cars);
			driverDao.create(driver);
			int j=driverDao.getAll().size();
			Assert.assertEquals(true,j==i+1);
			
		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test create driver ",e);
		}
	}
	@Test
	public void testUpdateDriverDao() {
		Driver driver = null;
		
		try {
			driver = driverDao.get(7);
			Car car=carDao.get(6);
			driver.getCars().add(car);
			log.info("test update driver begin");
			driverDao.update(driver);
			Assert.assertEquals(true, driverDao.get(7).getCars().size()==2);
			
		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test update driver ",e);
		}
	}
	@Test
	public void testDeleteDriverDao() {

		try {
			log.info("test delete driver begin");
			driverDao.delete(10);
			Assert.assertEquals(true, driverDao.get(10) == null);
     	} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test delete driver " + e);
		}
	}
	@Test
	public void testGetAllDriverDao() {
		List<Driver> drivers = null;
		try {
			log.info("test get all driver begin");
			drivers = driverDao.getAll();
			Assert.assertEquals(true, drivers.size() !=0);

		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get all driver " + e);
		}
	}
}
