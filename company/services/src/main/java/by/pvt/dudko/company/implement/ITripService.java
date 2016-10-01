package by.pvt.dudko.company.implement;

import java.util.List;

import by.pvt.dudko.company.dto.FilterTripClientDto;
import by.pvt.dudko.company.dto.PaginationDto;
import by.pvt.dudko.company.dto.SortTripDto;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Trip;

public interface ITripService {

	/**
	 * all trips
	 * 
	 * @return collection all trips
	 * 
	 */
	public List<Trip> allTrip() ;
	
	public int amountPages(PaginationDto paginationDto, int amount) ;
	
	public PaginationDto nextPage(PaginationDto paginationDto, int number) ;
	
	public int existTripClient(List<Trip> trips) ;
	
	/**
	 * 
	 * @param object
	 *            Client ,object SortTripDto,object FiltrTripClientDto,object PaginationDto
	 * @return collection trip
	 * 
	 */
	public List<Trip> pagination(Client client, SortTripDto sortTripDto, FilterTripClientDto filtrTripClientDto,
			PaginationDto paginationDto);
	
	/**
	 * 
	 * @param object
	 *            SortTripDto ,object FiltrTripClientDto,object client
	 * @return collection trip
	 *
	 */
	public List<Trip> sortTrip(SortTripDto sortTrip, FilterTripClientDto filtrTripClientDto, Client client, int max) ;
	
	/**
	 * 
	 * @param object
	 *            SortTripDto ,object FiltrTripClientDto,object client
	 * @return collection trip
	 * 
	 */
	public List<Trip> filtrTrip(Client client, FilterTripClientDto filtrTripClientDto, SortTripDto sortTripDto) ;
	
	/**
	 * method change condition trip
	 * 
	 * @param object
	 *            trip,condition(-1,0,1)
	 *
	 */
	public void changeCondTrip(Trip trip, int condition) ;
	
	/**
	 * method fixation trip in database
	 * 
	 * @param object
	 *            trip
	 * 
	 */
	public void fixationTrip(Trip trip) ;
	
	/**
	 * method delete trip and order in database
	 * 
	 * @param object
	 *            order
	 * 
	 */
	public void deleteTrip(Trip trip) ;
	
	/**
	 * method define all trip one client
	 * 
	 * @return collection trips
	 * @param object
	 *            client
	 * 
	 */
	public List<Trip> getTripUser(Client client) ;
	
	/**
	 * method get one trip
	 * 
	 * @return object trip
	 * @param id
	 *            trip
	 * 
	 */
	public Trip getTrip(int idTrip);
}
