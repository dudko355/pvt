package by.pvt.dudko.company.service;

import java.util.Date;
import java.util.List;

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
import by.pvt.dudko.company.dao.impl.OrderDao;
import by.pvt.dudko.company.dto.OrderDto;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.PropertiesOrder;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.DateException;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.implement.IOrderService;
import by.pvt.dudko.company.implement.IServiceService;
import by.pvt.dudko.company.util.CompanyDateUtil;
/**
 * OrderServiceImpl class 
 * business logic 
 * @author Aliaksei Dudko
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements IOrderService{
	private final int DATE_CORRECT=0;
	private final int DATE_INCORRECT=1;
	private final int CAR_NOT_FOUND=2;
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IServiceService serviceImpl;
	private static final Logger log = Logger.getLogger(OrderServiceImpl.class);

	public OrderServiceImpl() {
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Order> getAllOrder() {
		List<Order> list = orderDao.getAll();
		return list;
	}

	
	
	public int estimateDateOrder(OrderDto orderDto, Client client) {
		int result = DATE_INCORRECT;
		Date date = new Date();
		try {
			Date dateBegin=CompanyDateUtil.date(orderDto.getDateStart());
			Date dateFinish=CompanyDateUtil.date(orderDto.getDateFinish());
			if (dateFinish.after(dateBegin) && dateBegin.after(date)) {
				result = DATE_CORRECT;
			} else {
				log.info("INCORRECT DATA on the ORDER FORM");
			}
		} catch (Exception e) {
			result = CAR_NOT_FOUND;
		}
		return result;
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getNumberNextTrip() {
		int idOrder = orderDao.getMaxIdOrder() + 1;
		return idOrder;
	}


	public Order formOrder(Client client, OrderDto orderDto){
		PropertiesOrder propertiesOrder = new PropertiesOrder();
		propertiesOrder.setDateStart(CompanyDateUtil.date(orderDto.getDateStart()));
		propertiesOrder.setDateFinish(CompanyDateUtil.date(orderDto.getDateFinish()));
		propertiesOrder.setDistance(orderDto.getDistance());
		propertiesOrder.setMass(orderDto.getMass());
		propertiesOrder.setSeatCount(orderDto.getSeatCount());
		propertiesOrder.setOrderTarget(orderDto.getOrderTarget());
		propertiesOrder.setVolume(orderDto.getVolume());
		Order order = new Order();
		order.setClient(client);
		order.setPropertiesOrder(propertiesOrder);
		return order;

	}


	public void createOrder(Order order) {
		orderDao.create(order);
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Order getOrder(int idOrder) {
		Order order = (Order) orderDao.get(idOrder);
		return order;
	}
}
