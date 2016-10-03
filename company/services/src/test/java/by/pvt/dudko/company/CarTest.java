package by.pvt.dudko.company;

import java.util.List;
import java.util.Set;

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
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.implement.ICarService;
import by.pvt.dudko.company.service.CarServiceImpl;

@ContextConfiguration("/testContextServices.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class CarTest {
	
	@Autowired 
	private ICarService carServiceImpl;
	private static Logger log = Logger.getLogger(CarTest.class);
	
	
	@Test
	public void testGetAllCar() {

		try {
			log.info("test get all car in service begin");
			List<Car> allCar=carServiceImpl.getAllCar();
			Assert.assertEquals(true, allCar.size() !=0);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get all car in service" + e);
		}
	}
	@Test
	public void testConditionCar() {

		try {
			log.info("test conditionCar in service begin");
			Integer condition=carServiceImpl.getCarConditionById(1);
			Assert.assertEquals(true, condition != null);
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test conditionCar in service" + e);
		}
	}
	@Test
	public void testGetCar() {

		try {
			log.info("test getCar in service begin");
			Car car=carServiceImpl.getCar(1);
			Assert.assertEquals(true, car.getMass() == 5);
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test getCar in service" + e);
		}
	}
	@Test
	public void testCondCar() {

		try {
			log.info("test condCar in service begin");
			Car car=carServiceImpl.getCar(1);
			int cond=carServiceImpl.getCarConditionByCar(car);
			Assert.assertEquals(true, (cond == 0)||(cond == -1)||(cond == 1));
			
		} catch (Throwable e) {
			
			Assert.assertEquals(true, 5==4);
			log.error("Error test condCar in service" + e);
		}
	}
	@Test
	public void testChangeConditionCar() {

		try {
			log.info("test condCar in service begin");
			Car car=carServiceImpl.getCar(2);
			carServiceImpl.changeCarCondition(car, -1);
			
			Assert.assertEquals(true, carServiceImpl.getCarConditionById(2) == -1);
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test condCar in service" + e);
		}
	}
}
