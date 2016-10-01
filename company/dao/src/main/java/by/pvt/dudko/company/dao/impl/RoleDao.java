package by.pvt.dudko.company.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.pvt.dudko.company.dao.BaseDao;
import by.pvt.dudko.company.entities.Role;

/**
 * RoleDao class
 * operation with POJO Role 
 * @author Aliaksei Dudko
 *
 */

@Repository
public class RoleDao extends BaseDao<Role> {
	private static Logger log = Logger.getLogger(RoleDao.class);

	 @Autowired
	public RoleDao(SessionFactory sessionFactory){
	        super(sessionFactory);
	    }

}
