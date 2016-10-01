package by.pvt.dudko.company.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.pvt.dudko.company.dao.IDao;
import by.pvt.dudko.company.dao.impl.DriverDao;
import by.pvt.dudko.company.entities.Driver;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.implement.IDriverService;
/**
 * DriverServerImpl class 
 * business logic 
 * @author Aliaksei Dudko
 *
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DriverServerImpl implements IDriverService{
	@Autowired
	@Qualifier("driverDao")
	private IDao driverDao;
	private static final Logger log = Logger.getLogger(DriverServerImpl.class);

	public DriverServerImpl() {
	}


	public List<Driver> allDriver() {
		List<Driver> list = driverDao.getAll();
		return list;
	}
}
