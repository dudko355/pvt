package by.pvt.dudko.company.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.pvt.dudko.company.dao.IDao;
import by.pvt.dudko.company.dao.IOrderDao;
import by.pvt.dudko.company.dao.impl.OrderDao;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.implement.IAdminService;
import by.pvt.dudko.company.implement.ICarService;

/**
 * AdminServiceImpl class business logic
 * 
 * @author Aliaksei Dudko
 *
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AdminServiceImpl implements IAdminService {
	private final int busyCar = 1;
	private final int freeCar = 0;
	private final int brokenCar = -1;
	@Autowired
	private ICarService carServise;
	@Autowired
	private IOrderDao orderDao;
	private static final Logger log = Logger.getLogger(AdminServiceImpl.class);

	public AdminServiceImpl() {
	}

	public List<Car> getBusyCar() {
		Date date = new Date();
		List<Car> cars = carServise.getAllCar();
		List<Car> list = new ArrayList();
		for (Car car : cars) {
			car.getDriver();
			if (car.getCondition() == busyCar) {
				list.add(car);
			} else {
				for (Trip trip : car.getTrips()) {
					if (date.after(trip.getDateStart()) && date.before(trip.getDateFinish())) {
						list.add(car);
						break;
					}
				}
			}

		}

		return list;
	}

	public Car selectCar(Order order) throws ServiceException {
		List<Car> list = new ArrayList<Car>();
		Car car = null;
		List<Car> cars = carServise.getAllCar();
		for (Car car1 : cars) {
			if (car1.getCondition() == freeCar) {
				if (order.getPropertiesOrder().getMass() <= car1.getMass()
						&& order.getPropertiesOrder().getSeatCount() <= car1.getSeatCount()
						&& order.getPropertiesOrder().getVolume() <= car1.getVolume()) {
					Set<Trip> trips = (Set<Trip>) car1.getTrips();
					if (carServise.checkListTripsOnCoincidenceDatesWithOrder(trips, order)) {
						car = car1;
						break;
					}
				}
			}
		}
		if (car == null) {
			String message = "Car is not found";
			log.info(message);
			throw new ServiceException(message);
		}

		return car;
	}

	public Trip formTrip(Order order, Car car, Client client) {
		int tripNotStart=-1;
		Trip trip = new Trip();
		trip.setTripTarget(order.getPropertiesOrder().getOrderTarget());
		trip.setDateStart(order.getPropertiesOrder().getDateStart());
		trip.setDateFinish(order.getPropertiesOrder().getDateFinish());
		trip.setDistance(order.getPropertiesOrder().getDistance());
		trip.setCar(car);
		trip.setOrder(order);
		trip.setIdClient(client.getIdClient());
		trip.setIdTrip(orderDao.getMaxIdOrder() + 1);
		trip.setConditionTrip(tripNotStart);
		String message = "new trip is fill";
		log.info(message);
		return trip;
	}

	public List<Car> getBrokenCars() {
		List<Car> list = new ArrayList<Car>();
		for (Car car : carServise.getAllCar()) {
			if (carServise.getCarConditionByCar(car) == brokenCar) {
				list.add(car);
			}
		}
		return list;

	}

}
