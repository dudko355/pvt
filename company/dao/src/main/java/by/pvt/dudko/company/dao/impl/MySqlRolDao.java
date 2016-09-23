package by.pvt.dudko.company.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.pvt.dudko.company.dao.BaseDao;
import by.pvt.dudko.company.entities.Role;

@Repository
public class MySqlRolDao extends BaseDao<Role> {
	private static Logger log = Logger.getLogger(MySqlRolDao.class);

	 @Autowired
	public MySqlRolDao(SessionFactory sessionFactory){
	        super(sessionFactory);
	    }

}
