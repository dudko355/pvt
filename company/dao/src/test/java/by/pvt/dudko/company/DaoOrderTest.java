package by.pvt.dudko.company;

import java.util.List;
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
import by.pvt.dudko.company.dao.IOrderDao;
import by.pvt.dudko.company.dao.impl.OrderDao;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.PropertiesOrder;



@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional(propagation = Propagation.REQUIRED)
public class DaoOrderTest {
	@Autowired
	private IOrderDao orderDao;
	private static Logger log = Logger.getLogger(DaoOrderTest.class);
	
	


	@Test
	public void testGetOrderDao() {

		try {
			log.info("test get Order begin");
			Order order=orderDao.get(2);
			Assert.assertEquals(true, order.getIdOrder()== 2);
		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get Order ",e);
		}
	}
	@Test
	public void testCreateOrderDao() {
		Order order = null;
		
		try {
			int i=orderDao.getAll().size();
			Order order1 = orderDao.get(2);
			PropertiesOrder propertiesOrder=order1.getPropertiesOrder();
			propertiesOrder.setDistance(275);
			order = new Order(order1.getClient(),propertiesOrder);
			log.info("test create Order begin");
			orderDao.create(order);
			int j=orderDao.getAll().size();
			Assert.assertEquals(true, j==i+1);
		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test create order " + e);
		}
	}
	@Test
	public void testUpdateOrderDao() {
		Order order = null;
		try {
			order = orderDao.get(2);
			order.getPropertiesOrder().setMass(1);
			log.info("test update order begin");
			orderDao.update(order);
			Assert.assertEquals(true, orderDao.get(2).getPropertiesOrder().getMass() == 1);
		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test update order " + e);
		}
	}
	@Test
	public void testDeleteOrderDao() {

		try {
			
			log.info("test delete order begin");
			orderDao.delete(2);
			Assert.assertEquals(true, orderDao.get(2) == null);

		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test delete order " + e);
		}
	}
	@Test
	public void testGetAllOrderDao() {
		List<Order> orders = null;
		try {
			log.info("test get all orders begin");
			orders = orderDao.getAll();
			Assert.assertEquals(true, orders.get(0) != null);

		} catch (Exception e) {
			Assert.assertEquals(true, 5==4);
			log.error("Error test get all order " + e);
		}
	}
	@Test
	public void testMaxIdOrderDao() {
		try {
			int maxId = orderDao.getMaxIdOrder();
			Assert.assertEquals(true, maxId != 0);
			log.info(maxId);
		} catch (Exception e) {
			Assert.assertEquals(true, 5 == 4);
			log.error("Error test get max id order " + e);
		}
	}

}
