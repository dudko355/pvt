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
import by.pvt.dudko.company.dto.PaginationDto;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.service.CarServiceImpl;
import by.pvt.dudko.company.service.ClientServiceImpl;
import by.pvt.dudko.company.service.OrderServiceImpl;
import by.pvt.dudko.company.service.TripServiceImpl;

@ContextConfiguration("/testContextServices.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class TripTest {
	@Autowired 
	private CarServiceImpl carServiceImpl;
	@Autowired
	private TripServiceImpl tripServiceImpl;
	@Autowired 
	private ClientServiceImpl clientServiceImpl;
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	private static Logger log = Logger.getLogger(TripTest.class);
	
	@Before
	public void init() {

	}

	@After
	public void destroy() {
	
      }
	
	@Test
	public void testGetAllTrip() {

		try {
			log.info("test get all trip in service begin");
			List<Trip> allTrip=tripServiceImpl.allTrip();
			Assert.assertEquals(true, allTrip!=null);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get all trip in service" + e);
		}
	}
	
	@Test
	public void testAmountPages() {
	try{
		PaginationDto paginationDto=new PaginationDto();
		paginationDto.setAllCount(6);
		int i =tripServiceImpl.amountPages(paginationDto, 2);
		Assert.assertEquals(true, i==3);
	} catch (Throwable e) {
		Assert.assertEquals(true, 5==4);
		log.error("Error test get all trip in service" + e);
	}
}
	

	@Test
	public void testNextPage() {
	try{
		PaginationDto paginationDto=new PaginationDto();
		paginationDto.setAllCount(6);
		paginationDto.setCount(2);
		paginationDto.setStart(1);
		tripServiceImpl.nextPage(paginationDto, 1);
		Assert.assertEquals(true, paginationDto.getStart()==3);
		tripServiceImpl.nextPage(paginationDto, 0);
		Assert.assertEquals(true, paginationDto.getStart()==1);
	} catch (Throwable e) {
		Assert.assertEquals(true, 5==4);
		log.error("Error test get all trip in service" + e);
	}
}
	
	@Test
	public void testChangeCondTrip() {

		try {
			log.info("test changeCondTrip in service begin");
			Trip trip=tripServiceImpl.allTrip().get(0);
			tripServiceImpl.changeCondTrip(trip, 0);
			Assert.assertEquals(true, tripServiceImpl.allTrip().get(0).getConditionTrip() == 0);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test changeCondTrip in service" + e);
		}
	}
	@Test
	public void testGetTripUser() {

		try {
			log.info("test getTripUser in service begin");
			Client client=clientServiceImpl.allClient().get(2);
			List<Trip> list=tripServiceImpl.getTripUser(client);
			Assert.assertEquals(true, list!=null);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test getTripUser in service" + e);
		}
	}
	@Test
	public void testGetTrip() {

		try {
			log.info("test GetTrip in service begin");
			Trip trip=tripServiceImpl.getTrip(2);
			Assert.assertEquals(true, trip.getDictanse() !=0);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test GetTrip in service" + e);
		}
	}
	
	@Test
	public void testFixTrip() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			log.info("test FixTrip in service begin");
			int size=tripServiceImpl.allTrip().size();
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
			orderServiceImpl.fixationOrder(order);
			Trip trip=new Trip();
			trip.setCar(carServiceImpl.allCar().get(0));
			trip.setConditionTrip(-1);
			trip.setDateBegin(sdf.parse("2016-10-12"));
			trip.setDateFinish(sdf.parse("2016-10-15"));
			trip.setDictanse(29);
			trip.setIdClient(client.getIdClient());
			trip.setIdOrder(order.getIdOrder());
			trip.setIdTrip(order.getIdOrder());
			trip.setOrder(order);
			trip.setTarget("vena");
			tripServiceImpl.fixationTrip(trip);
			Assert.assertEquals(true, tripServiceImpl.allTrip().size() == size+1);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test FormAndFixOrder in service" + e);
		}
	}
	@Test
	public void testDeleteTrip() {

		try {
			log.info("test delete trip in service begin");
			int size=tripServiceImpl.allTrip().size();
			Trip trip=tripServiceImpl.getTrip(2);
			tripServiceImpl.deleteTrip(trip);
			Assert.assertEquals(true, tripServiceImpl.allTrip().size() == size-1);
			
		} catch (Throwable e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test delete trip in service" + e);
		}
	}
}
