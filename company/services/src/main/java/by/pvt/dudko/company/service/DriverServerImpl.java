package by.pvt.dudko.company.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.pvt.dudko.company.dao.IDao;
import by.pvt.dudko.company.dao.impl.MySqlDriverDao;
import by.pvt.dudko.company.entities.Driver;
import by.pvt.dudko.company.exception.ServiceException;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DriverServerImpl {
	@Autowired
	@Qualifier("mySqlDriverDao")
	private IDao mySqlDriverDao;
	private static final Logger log = Logger.getLogger(DriverServerImpl.class);

	public DriverServerImpl() {
	}

	/**
	 * all drivers
	 * 
	 * @return collection all driver
	 * 
	 */

	public List<Driver> allDriver() {
		List<Driver> list = mySqlDriverDao.getAll();
		return list;
	}
}
