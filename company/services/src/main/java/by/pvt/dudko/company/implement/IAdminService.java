package by.pvt.dudko.company.implement;

import java.util.List;

import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.ServiceException;

public interface IAdminService {
	
	/**
	 * method define broken car
	 * @return collection car
	 * 
	 */
	public List<Car> getBrokenCars();
	
	/**
	 * method generates an trip
	 * @return object trip
	 * @param objects
	 *            order,car,client
	 */
	public Trip formTrip(Order order, Car car, Client client);
	
	/**
	 * method choose car for order
	 * 
	 * @return object car
	 * @param object
	 *            order
	 * @throws IllegalArgumentException
	 * @throws ServiceException
	 */
	public Car selectCar(Order order) throws ServiceException;
	
	public List<Car> getBusyCar();
	
}
