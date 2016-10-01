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
import by.pvt.dudko.company.dao.impl.CarDao;
import by.pvt.dudko.company.dao.impl.OrderDao;
import by.pvt.dudko.company.dao.impl.TripDao;
import by.pvt.dudko.company.dto.OrderDto;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.DateException;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.implement.IAdminService;
import by.pvt.dudko.company.implement.IOrderService;
import by.pvt.dudko.company.implement.IServiceService;
/**
 * ServiceImpl class 
 * business logic 
 * make trip
 * @author Aliaksei Dudko
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ServiceImpl implements IServiceService{
	@Autowired
	private IOrderService orderServiceImpl;
	@Autowired
	private IAdminService adminServiceImpl;
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private ITripDao tripDao;
	@Autowired
	@Qualifier("carDao")
	private IDao carDao;
	private static final Logger log = Logger.getLogger(ServiceImpl.class);

	public ServiceImpl() {
	}



	public Car transactionSaveTrip(Client client, OrderDto orderDto) throws ServiceException{
		Order order = orderServiceImpl.formOrder(client, orderDto);
		Car car = adminServiceImpl.selectCar(order);
		Trip trip = adminServiceImpl.formTrip(order, car, client);
		orderDao.create(order);
		tripDao.create(trip);
		log.info("Tranzakzion ServiceImpl is good,order is fixation in the database");
		return car;
	}
}
