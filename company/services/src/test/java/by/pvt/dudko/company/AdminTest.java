package by.pvt.dudko.company;

import java.text.SimpleDateFormat;
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

import by.pvt.dudko.company.dto.OrderDto;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.implement.IAdminService;
import by.pvt.dudko.company.implement.IClientService;
import by.pvt.dudko.company.implement.IOrderService;
import by.pvt.dudko.company.service.AdminServiceImpl;
import by.pvt.dudko.company.service.ClientServiceImpl;
import by.pvt.dudko.company.service.OrderServiceImpl;
import by.pvt.dudko.company.service.TripServiceImpl;

@ContextConfiguration("/testContextServices.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminTest {
	
	@Autowired 
	private IAdminService adminServiceImpl;
	@Autowired 
	private IClientService clientServiceImpl;
	@Autowired 
	private IOrderService orderServiceImpl;
	private static Logger log = Logger.getLogger(AdminTest.class);
	
	
	@Test
	public void testSelectCar() {
		try {
			OrderDto orderDto=new OrderDto();
			orderDto.setDateStart("10/12/2016");
			orderDto.setDateFinish("10/15/2016");
			orderDto.setDistance(29);
			orderDto.setMass(2);
			orderDto.setSeatCount(1);
			orderDto.setOrderTarget("vena");
			orderDto.setVolume(2);
			Client client=clientServiceImpl.getUser("alex", "111111");	
			Order order=orderServiceImpl.formOrder(client, orderDto);
			Car car=adminServiceImpl.selectCar(order);
			Assert.assertEquals(true, car != null);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test ScheduleCar in service" + e);
		}
	}
	@Test
	
	public void testFormTrip() {
		try {
			OrderDto orderDto=new OrderDto();
			orderDto.setDateStart("10/12/2016");
			orderDto.setDateFinish("10/15/2016");
			orderDto.setDistance(29);
			orderDto.setMass(2);
			orderDto.setSeatCount(1);
			orderDto.setOrderTarget("vena");
			orderDto.setVolume(2);
			Client client=clientServiceImpl.getUser("alex", "111111");	
			Order order=orderServiceImpl.formOrder(client, orderDto);
			Car car=adminServiceImpl.selectCar(order);
			Trip trip=adminServiceImpl.formTrip(order, car, client);
			Assert.assertEquals(true, trip != null);
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test ScheduleTrip in service" + e);
		}
	}
	@Test
	public void testBrokenCars() {
		
		try {
			List<Car> list=adminServiceImpl.getBrokenCars();
			Assert.assertEquals(true, list.size()!=0);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test brokenCars in service" + e);
		}
	}
}
