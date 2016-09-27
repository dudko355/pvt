package by.pvt.dudko.company.dao;

import java.util.List;

import by.pvt.dudko.company.dto.FilterTripClientDto;
import by.pvt.dudko.company.dto.PaginationDto;
import by.pvt.dudko.company.dto.SortTripDto;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Trip;

public interface ITripDao extends IDao<Trip>{
	/**
	 * 
	 * @param object Client
	 * @return collection trip
	 * @throws DataAccessException(Spring)
	 */
	public List<Trip> getTripsClient(Client client);
	/**
	 * 
	 * @param object SortTripDto ,object FiltrTripDto,object client,max count element on the page
	 * @return collection trip
	 * @throws DataAccessException(Spring)
	 */
	public List<Trip> sortTripClient(SortTripDto sortOrder, FilterTripClientDto filtrOrder, Client client,int max);
	/**
	 * 
	 * @param object Client ,object SortTripDto,object FiltrTripClientDto,object PaginationDto
	 * @return collection trip
	 * @throws DataAccessException(Spring)
	 */
	public List<Trip> paginationDao(Client client, SortTripDto sortTrip, FilterTripClientDto filtrOrder, PaginationDto pagination);
	/**
	 * 
	 * @param object Client ,filtrTrip,sortTripDto
	 * @return collection trip
	 * @throws DataAccessException(Spring)
	 */
	public List<Trip> getFiltrTrips(Client client, FilterTripClientDto filtrTrip, SortTripDto sortTrip);
}
