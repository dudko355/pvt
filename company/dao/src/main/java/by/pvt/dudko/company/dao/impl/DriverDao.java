package by.pvt.dudko.company.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.pvt.dudko.company.dao.BaseDao;
import by.pvt.dudko.company.entities.Driver;

/**
 * DriverDao class
 * operation with POJO Driver 
 * @author Aliaksei Dudko
 *
 */

@Repository
public class DriverDao extends BaseDao<Driver> {
	private static Logger log = Logger.getLogger(DriverDao.class);

	@Autowired
	public DriverDao(SessionFactory sessionFactory){
	        super(sessionFactory);
	    }

}
