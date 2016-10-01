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
import by.pvt.dudko.company.dao.IOrderDao;
import by.pvt.dudko.company.dao.ITripDao;
import by.pvt.dudko.company.dao.impl.TripDao;
import by.pvt.dudko.company.dto.FilterTripClientDto;
import by.pvt.dudko.company.dto.SortTripDto;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.util.CompanyDateUtil;

@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class DaoTripTest {
	@Autowired
	private ITripDao tripDao;
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IClientDao clientDao;
	private static Logger log = Logger.getLogger(DaoTripTest.class);

	@Before
	public void init() {

	}

	@After
	public void destroy() {

	}

	@Test
	public void testGetTripDao() {

		try {
			log.info("test get Trip begin");
			Assert.assertEquals(true, tripDao.get(2).getDistance() == 100);
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test get Trip " + e);
		}
	}

	// @Test
	// public void testCreateTripDao() {
	// Trip trip = null;
	// Session session = HibernateUtil.getHibernateUtil().getSession();
	// Transaction transaction = null;
	// try {
	// //transaction = session.beginTransaction();
	// int i=tripDao.getAll().size();
	// Order order1 = orderDao.get(2);
	// PropertiesOrder propertiesOrder=order1.getPropertiesOrder();
	// propertiesOrder.setDictanse(275);
	// Order order2 = new Order(order1.getClient(),propertiesOrder);
	// trip = new Trip(propertiesOrder.getTarget(), 275, 2, 100,
	// order2.getPropertiesOrder().getDateBegin(),
	// order2.getPropertiesOrder().getDateFinish(), 0,
	// MySqlCarDao.getInstance().get(11), order2);
	//
	// order2.setTrip(trip);
	// orderDao.create(order2);
	// //tripDao.create(trip);
	// int j=tripDao.getAll().size();
	// //transaction.commit();
	// Assert.assertEquals(true, j == i+1);
	// } catch (DaoException e) {
	// //transaction.rollback();
	// Assert.assertEquals(true, 5 == 4);
	// log.error("Error test CreateTripDao " + e);
	// }
	// }

	@Test
	public void testSortTripClient() {
		try {
			SortTripDto sortTrip = new SortTripDto();
			sortTrip.setColumn("dateStart");
			sortTrip.setOrderColumn("ASC");
			Client client = clientDao.get(2);
			List<Trip> trips = tripDao.sortTripClient(sortTrip, null, client,2);
			Assert.assertEquals(true, trips != null);
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test update trip " + e);
		}
	}

	@Test
	public void testGetFiltrTrips() {
		try {
			Client client = clientDao.get(2);
			FilterTripClientDto filtrTrip = new FilterTripClientDto();
			filtrTrip.setDateStart(CompanyDateUtil.date("15/06/2016"));
			filtrTrip.setDateFinish(CompanyDateUtil.date("15/08/2017"));
			filtrTrip.setTripTarget("");
			List<Trip> trips = tripDao.getFiltrTrips(client, filtrTrip, null);
			Assert.assertEquals(true, trips != null);
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test update trip " + e);
		}
	}

	@Test
	public void testUpdateTripDao() {
		Trip trip = null;

		try {
			trip = tripDao.get(2);
			trip.setDistance(400);
			log.info("test update trip begin");
			tripDao.update(trip);
			Assert.assertEquals(true, tripDao.get(2).getDistance() == 400);
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test update trip " + e);
		}
	}

	@Test
	public void testDeleteTripDao() {

		try {
			log.info("test delete trip begin");
			tripDao.delete(2);
			Assert.assertEquals(true, tripDao.get(2) == null);
		} catch (Exception e) {

		}
	}

	@Test
	public void testGetAllTripDao() {
		List<Trip> trips = null;
		try {
			log.info("test get all trip begin");
			trips = tripDao.getAll();
			Assert.assertEquals(true, trips != null);
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test get all trip " + e);
		}
	}

	@Test
	public void testGetClientTripDao() {

		try {

			Client client = clientDao.get(2);
			log.info("test get all trip client begin");
			List<Trip> trips = (List<Trip>) tripDao.getTripsClient(client);
			Assert.assertEquals(true, trips.size() != 0);
			log.info("test all trip client succesfully");
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test get client trip " + e);
		}
	}
}
