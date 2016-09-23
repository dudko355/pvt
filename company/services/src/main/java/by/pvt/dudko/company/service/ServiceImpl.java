package by.pvt.dudko.company.service;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.pvt.dudko.company.dao.IDao;
import by.pvt.dudko.company.dao.IOrderDao;
import by.pvt.dudko.company.dao.ITripDao;
import by.pvt.dudko.company.dao.impl.MySqlCarDao;
import by.pvt.dudko.company.dao.impl.MySqlOrderDao;
import by.pvt.dudko.company.dao.impl.MySqlTripDao;
import by.pvt.dudko.company.dto.OrderDto;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.ServiceException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ServiceImpl {
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	@Autowired
	private IOrderDao mySqlOrderDao;
	@Autowired
	private ITripDao mySqlTripDao;
	@Autowired
	@Qualifier("mySqlCarDao")
	private IDao mySqlCarDao;
	private static final Logger log = Logger.getLogger(ServiceImpl.class);

	public ServiceImpl() {
	}

	/**
	 * method by fixing the application transaction
	 * 
	 * @param client,orderDto
	 * @throws ServiceException
	 */

	public void transactionSaveTrip(Client client, OrderDto orderDto) throws ServiceException {
		Order order = orderServiceImpl.formOrder(client, orderDto);
		Car car = adminServiceImpl.selectCar(order);
		Trip trip = adminServiceImpl.formTrip(order, car, client);
		mySqlOrderDao.create(order);
		mySqlTripDao.create(trip);
		log.info("Tranzakzion ServiceImpl is good,order is fixation in the database");
	}
}
