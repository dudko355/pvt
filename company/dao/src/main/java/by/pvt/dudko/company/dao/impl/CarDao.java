package by.pvt.dudko.company.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import by.pvt.dudko.company.dao.BaseDao;
import by.pvt.dudko.company.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * CarDao class
 * operation with POJO Car 
 * @author Aliaksei Dudko
 *
 */


@Repository
public class CarDao extends BaseDao<Car> {
	private static Logger log = Logger.getLogger(CarDao.class);
	
	@Autowired
	public CarDao(SessionFactory sessionFactory){
	        super(sessionFactory);
	    }

}
