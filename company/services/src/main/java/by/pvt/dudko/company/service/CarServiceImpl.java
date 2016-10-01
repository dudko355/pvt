package by.pvt.dudko.company.service;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.pvt.dudko.company.dao.IDao;
import by.pvt.dudko.company.dao.impl.CarDao;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.implement.ICarService;
/**
 * CarServiceImpl class 
 * business logic 
 * @author Aliaksei Dudko
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CarServiceImpl implements ICarService {

	@Autowired
	@Qualifier("carDao")
	private IDao carDao;
	private static final Logger log = Logger.getLogger(CarServiceImpl.class);

	public CarServiceImpl() {
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Car> getAllCar() {
		List<Car> list = carDao.getAll();
		return list;

	}


	public boolean checkListTripsOnCoincidenceDatesWithOrder(Set<Trip> list, Order order) {
		boolean notCoincidence = false;
		int amount = 0;
		for (Trip trip : list) {
			if (trip.getDateStart().after(order.getPropertiesOrder().getDateFinish())
					|| trip.getDateFinish().before(order.getPropertiesOrder().getDateStart())) {
				amount = amount + 1;
			}
		}
		if (amount == list.size()) {
			notCoincidence = true;
		}
		return notCoincidence;

	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCarConditionById(int key) {
		Car car = (Car) carDao.get(key);
		return car.getCondition();
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Car getCar(int key) {
		Car car = (Car) carDao.get(key);
		return car;

	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCarConditionByCar(Car car) {
		return getCarConditionById(car.getIdCar());
	}


	public void changeCarCondition(Car car, int condition) {
		car.setCondition(condition);
		carDao.update(car);
	}
}