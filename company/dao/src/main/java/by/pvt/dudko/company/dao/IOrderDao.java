package by.pvt.dudko.company.dao;

import by.pvt.dudko.company.entities.Order;

public interface IOrderDao extends IDao<Order> {
	
	/**
	 * @return max id Order
	 * @throws DataAccessException(Spring)
	 */
	public int getMaxIdOrder();
}
