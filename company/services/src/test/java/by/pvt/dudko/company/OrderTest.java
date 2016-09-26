package by.pvt.dudko.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import by.pvt.dudko.company.dao.impl.MySqlCarDao;
import by.pvt.dudko.company.dto.OrderDto;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Driver;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.service.CarServiceImpl;
import by.pvt.dudko.company.service.ClientServiceImpl;
import by.pvt.dudko.company.service.DriverServerImpl;
import by.pvt.dudko.company.service.OrderServiceImpl;
import by.pvt.dudko.company.util.UtilDate;

@ContextConfiguration("/testContextServices.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class OrderTest {
	
	@Autowired 
	private CarServiceImpl carServiceImpl;
	@Autowired 
	private ClientServiceImpl clientServiceImpl;
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	private static Logger log = Logger.getLogger(OrderTest.class);
	
	@Before
	public void init() {
		
	}

	@After
	public void destroy() {
		orderServiceImpl = null;
		carServiceImpl=null;
		clientServiceImpl=null;
      }
	
	@Test
	public void testGetAllOrder() {

		try {
			log.info("test get all order in service begin");
			List<Order> allOrder=orderServiceImpl.allOrder();
			Assert.assertEquals(true, allOrder.get(0)!=null);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get all order in service" + e);
		}
	}
	@Test
	public void testNumberOrder() {

		try {
			log.info("test get numberOrder in service begin");
			int number=orderServiceImpl.numberTrip();
			Assert.assertEquals(true, number !=0);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get numberOrder in service" + e);
		}
	}
	
	@Test
	public void testgetOrder() {

		try {
			
			Order order=orderServiceImpl.getOrder(2);
			Assert.assertEquals(true, order!=null);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get numberOrder in service" + e);
		}
	}
	@Test
	public void testFormAndFixOrder() {
			
		try {
			log.info("test FormAndFixOrder in service begin");
			int size=orderServiceImpl.allOrder().size();
			OrderDto orderDto=new OrderDto();
			orderDto.setDateBegin("10/12/2016");
			orderDto.setDateFinish("10/15/2016");
			orderDto.setDictanse(29);
			orderDto.setMass(2);
			orderDto.setSeatCount(1);
			orderDto.setTarget("vena");
			orderDto.setVolume(2);
			Client client=clientServiceImpl.getUser("alex", "111111");	
			Order order=orderServiceImpl.formOrder(client, orderDto);
			Trip trip = new Trip(orderDto.getTarget(), 275, 2, 102, UtilDate.date(orderDto.getDateBegin()),
					UtilDate.date(orderDto.getDateFinish()), 0, carServiceImpl.getCar(1), order);
			order.setTrip(trip);
			orderServiceImpl.fixationOrder(order);
			Assert.assertEquals(true, orderServiceImpl.allOrder().size() == size+1);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test FormAndFixOrder in service" + e);
		}
	}
}
