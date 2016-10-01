
package by.pvt.dudko.company.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.pvt.dudko.company.dao.BaseDao;
import by.pvt.dudko.company.dao.IClientDao;
import by.pvt.dudko.company.dao.IOrderDao;
import by.pvt.dudko.company.entities.Order;

/**
 * OrderDao class
 * operation with POJO Order 
 * @author Aliaksei Dudko
 *
 */

@Repository
public class OrderDao extends BaseDao<Order> implements IOrderDao{
	private static Logger log = Logger.getLogger(OrderDao.class);

	@Autowired
	public OrderDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	
	public int getMaxIdOrder() {
		log.info("(attempt) get max id order");
		int maxIdOrder = (int) getSession().createCriteria(Order.class).setProjection(Projections.max("idOrder"))
				.uniqueResult();
		log.info(" get max id order successfully:" + maxIdOrder);
		return (int) maxIdOrder;
	}
}
