package by.pvt.dudko.company.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import by.pvt.dudko.company.dao.BaseDao;
import by.pvt.dudko.company.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MySqlCarDao extends BaseDao<Car> {
	private static Logger log = Logger.getLogger(MySqlCarDao.class);
	
	@Autowired
	public MySqlCarDao(SessionFactory sessionFactory){
	        super(sessionFactory);
	    }

}
