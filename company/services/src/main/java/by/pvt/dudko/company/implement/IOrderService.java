package by.pvt.dudko.company.implement;

import java.util.List;

import by.pvt.dudko.company.dto.OrderDto;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.exception.DateException;
import by.pvt.dudko.company.exception.ServiceException;

public interface IOrderService {

	/**
	 * all orders
	 * 
	 * @return collection all orders
	 */
	public List<Order> getAllOrder();
	
	/**
	 * method for comparing dates of existing trips in the car with the date of
	 * the new trip
	 * 
	 * @param client,orderDto
	 * @return int 0-if car is find,1 - if data is incorrect,2-if don't suitable
	 *         car
	 */
	public int estimateDateOrder(OrderDto orderDto, Client client);
	
	/**
	 * method define id next order
	 * 
	 * @return id Order
	 */
	public int getNumberNextTrip();
	
	/**
	 * method generates an order
	 * 
	 * @param client,orderDto
	 * @return object Order
	 */
	public Order formOrder(Client client, OrderDto orderDto);
	
	/**
	 * method write object order in database
	 * @param order
	 */
	public void createOrder(Order order);
	
	/**
	 * method get one order
	 * 
	 * @return object order
	 * @param id
	 *            order
	 */
	public Order getOrder(int idOrder) ;
	
}
