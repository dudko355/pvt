package by.pvt.dudko.company.implement;

import java.util.List;
import java.util.Set;

import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.Trip;

public interface ICarService {

	/**
	 * All cars
	 * @return collection all car
	 *
	 */
	public List<Car> getAllCar();
	
	/**
	 * equals date which car do and date of order
	 * 
	 * @param object
	 *            car and order
	 * @return boolean(true if car free,else false)
	 * 
	 */
	public boolean checkListTripsOnCoincidenceDatesWithOrder(Set<Trip> list, Order order);
	
	/**
	 * definition of key condition machines
	 * 
	 * @param key
	 *            - id of Car
	 * @return condition Car
	 * 
	 */
	public int getCarConditionById(int key);
	
	/**
	 * definition of key machines
	 * 
	 * @param key
	 *            - id of Car
	 * @return entity Car
	 * 
	 */
	public Car getCar(int key);
	
	/**
	 * definition of machines condition car
	 * 
	 * @param object
	 *            Car
	 * @return condition Car(-1,0,1)
	 *
	 */
	public int getCarConditionByCar(Car car) ;
	
	/**
	 * change condition car
	 * 
	 * @param object
	 *            Car and condition,that should be
	 * 
	 */
	public void changeCarCondition(Car car, int condition) ;
	
}
