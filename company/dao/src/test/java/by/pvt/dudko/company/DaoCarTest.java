package by.pvt.dudko.company;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

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
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Driver;

@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class DaoCarTest {
	@Autowired
	@Qualifier("carDao")
	private IDao<Car> carDao;
	@Autowired
	@Qualifier("driverDao")
	private IDao<Driver> driverDao;
	private static Logger log = Logger.getLogger(DaoCarTest.class);
	

	@Before
	public void init() {
		
	}

	@After
	public void destroy() {
		
	}

	@Test
	public void testGetCar() {

		try {
			log.info("test get car begin");
			Car car = (Car) carDao.get(1);
			Assert.assertEquals(true, car.getMass() == 5);
		} catch (Throwable e) {
			e.printStackTrace();
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test get car ", e);
		}
	}

//	@Test
//	public void testCreateCarDao() {
//		try {
//			int i = carDao.getAll().size();
//			Car car = new Car("volvo", 2, 20, 20, 0);
//			Set<Driver> driver = new HashSet<Driver>();
//			driver.add((Driver) driverDao.get(3));
//			car.setDriver(driver);
//			log.info("test create car begin");
//			carDao.create(car);
//			int j = carDao.getAll().size();
//			Assert.assertEquals(true, j == i + 1);
//		} catch (Exception e) {
//			Assert.assertEquals(true, 5 == 4);
//			log.error("Error test create car " + e);
//		}
//	}

	@Test
	public void testUpdateCarDao() {
		Car car = null;
		try {
			car = (Car) carDao.get(2);
			car.setMass(70);
			log.info("test update car begin");
			carDao.update(car);
			Assert.assertEquals(true, ((Car) carDao.get(2)).getMass() == 70);
			
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test update car " + e);
		}
	}

	@Test
	public void testDeleteCarDao() {

		try {
			carDao.delete(7);
			log.info("test delete car begin");
			Assert.assertEquals(true, carDao.get(7) == null);
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test delete car " + e);
		}
	}

	@Test
	public void testGetAllCarDao() {
		List<Car> cars = null;
		try {
			cars = carDao.getAll();
			log.info("test get all car begin");
			Assert.assertEquals(true, cars != null);
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test get all car " + e);
		}
	}
}
