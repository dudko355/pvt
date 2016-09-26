package by.pvt.dudko.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.service.AdminServiceImpl;
import by.pvt.dudko.company.service.ClientServiceImpl;
import by.pvt.dudko.company.service.OrderServiceImpl;
import by.pvt.dudko.company.service.ServiceImpl;
import by.pvt.dudko.company.service.TripServiceImpl;

@ContextConfiguration("/testContextServices.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class ServiceTest {
	@Autowired
	private ClientServiceImpl clientServiceImpl;
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	@Autowired
	private TripServiceImpl tripServiceImpl;
	@Autowired
	private ServiceImpl serviceImpl;
	private static Logger log = Logger.getLogger(ServiceTest.class);

	@Before
	public void init() {

	}

	@After
	public void destroy() {

	}

	@Test(expected = ServiceException.class)
	public void testTransactionSaveTripError() throws ParseException, ServiceException {
		int sizeTrip = tripServiceImpl.allTrip().size();
		int sizeOrder = orderServiceImpl.allOrder().size();
		OrderDto orderDto = new OrderDto();
		orderDto.setDateBegin("10/12/2016");
		orderDto.setDateFinish("10/15/2016");
		orderDto.setDictanse(29);
		orderDto.setMass(200);
		orderDto.setSeatCount(1);
		orderDto.setTarget("vena");
		orderDto.setVolume(2);
		Client client = clientServiceImpl.getUser("alex", "111111");
		serviceImpl.transactionSaveTrip(client, orderDto);
		Assert.assertEquals(true, tripServiceImpl.allTrip().size() == sizeTrip + 1);
		Assert.assertEquals(true, orderServiceImpl.allOrder().size() == sizeOrder + 1);

	}

	@Test
	public void testTransactionSaveTrip() {

		try {
			int sizeTrip = tripServiceImpl.allTrip().size();
			int sizeOrder = orderServiceImpl.allOrder().size();
			OrderDto orderDto = new OrderDto();
			orderDto.setDateBegin("10/12/2016");
			orderDto.setDateFinish("10/15/2016");
			orderDto.setDictanse(29);
			orderDto.setMass(2);
			orderDto.setSeatCount(1);
			orderDto.setTarget("vena");
			orderDto.setVolume(2);
			Client client = clientServiceImpl.getUser("alex", "111111");
			serviceImpl.transactionSaveTrip(client, orderDto);
			Assert.assertEquals(true, tripServiceImpl.allTrip().size() == sizeTrip + 1);
			Assert.assertEquals(true, orderServiceImpl.allOrder().size() == sizeOrder + 1);
		} catch (Throwable e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test ScheduleCar in service" + e);
		}
	}
}
