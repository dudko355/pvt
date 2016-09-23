package by.pvt.dudko.company.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.pvt.dudko.company.dao.BaseDao;
import by.pvt.dudko.company.entities.Driver;

@Repository
public class MySqlDriverDao extends BaseDao<Driver> {
	private static Logger log = Logger.getLogger(MySqlDriverDao.class);

	@Autowired
	public MySqlDriverDao(SessionFactory sessionFactory){
	        super(sessionFactory);
	    }

}
