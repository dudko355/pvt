package by.pvt.dudko.company.dao;

import java.util.List;

import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.FiltrTrip;
import by.pvt.dudko.company.entities.Pagination;
import by.pvt.dudko.company.entities.SortTrip;
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
	 * @param object SortTrip ,object FiltrTrip,object client,max count element on the page
	 * @return collection trip
	 * @throws DataAccessException(Spring)
	 */
	public List<Trip> sortTripClient(SortTrip sortOrder, FiltrTrip filtrOrder, Client client,int max);
	/**
	 * 
	 * @param object Client ,object SortTrip,object FiltrTripClient,object Pagination
	 * @return collection trip
	 * @throws DataAccessException(Spring)
	 */
	public List<Trip> paginationDao(Client client, SortTrip sortTrip, FiltrTrip filtrOrder, Pagination pagination);
	/**
	 * 
	 * @param object Client ,filtrTrip,sortTrip
	 * @return collection trip
	 * @throws DataAccessException(Spring)
	 */
	public List<Trip> getFiltrTrips(Client client, FiltrTrip filtrTrip, SortTrip sortTrip);
}
