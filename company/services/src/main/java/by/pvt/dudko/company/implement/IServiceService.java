package by.pvt.dudko.company.implement;

import by.pvt.dudko.company.dto.OrderDto;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.DateException;
import by.pvt.dudko.company.exception.ServiceException;

public interface IServiceService {

	/**
	 * method by fixing the application transaction
	 * 
	 * @param client,orderDto
	 * @throws ServiceException
	 * @throws DateException 
	 */
	public Car transactionSaveTrip(Client client, OrderDto orderDto) throws ServiceException;
}
