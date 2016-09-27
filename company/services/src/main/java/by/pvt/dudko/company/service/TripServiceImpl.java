package by.pvt.dudko.company.service;


import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.pvt.dudko.company.dao.IDao;
import by.pvt.dudko.company.dao.IOrderDao;
import by.pvt.dudko.company.dao.ITripDao;
import by.pvt.dudko.company.dto.FilterTripClientDto;
import by.pvt.dudko.company.dto.PaginationDto;
import by.pvt.dudko.company.dto.SortTripDto;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Trip;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TripServiceImpl {

	@Autowired
	private IOrderDao mySqlOrderDao;
	@Autowired
	private ITripDao mySqlTripDao;
	@Autowired
	@Qualifier("mySqlCarDao")
	private IDao mySqlCarDao;
	@Autowired
	private CarServiceImpl carServiceImpl;
	private static final Logger log = Logger.getLogger(TripServiceImpl.class);

	public TripServiceImpl() {
	}

	/**
	 * all trips
	 * 
	 * @return collection all trips
	 * 
	 */
	public List<Trip> allTrip() {
		List<Trip> list = mySqlTripDao.getAll();
		return list;
	}

	public int amountPages(PaginationDto paginationDto, int amount) {
		int allPage = 1;
		if (paginationDto.getAllCount() % amount == 0) {
			allPage = paginationDto.getAllCount() / amount;
		} else {
			allPage = (int) paginationDto.getAllCount() / amount + 1;
		}
		return allPage;
	}

	public PaginationDto nextPage(PaginationDto paginationDto, int number) {
		int start = 0;
		if (number == 1) {
			start = paginationDto.getStart() + paginationDto.getCount();
			paginationDto.setStart(start);
			paginationDto.setPage(paginationDto.getPage() + 1);
		} else if (number == 0) {
			start = paginationDto.getStart() - paginationDto.getCount();
			paginationDto.setStart(start);
			paginationDto.setPage(paginationDto.getPage() - 1);
		}
		return paginationDto;

	}

	public int existTripClient(List<Trip> trips) {
		int i = 0;
		for (Trip trip : trips) {
			if (trip.getConditionTrip() == (1)) {
				deleteTrip(trip);
			} else {
				i = i + 1;
			}
		}
		return i;

	}

	/**
	 * 
	 * @param object
	 *            Client ,object SortTripDto,object FiltrTripClientDto,object
	 *            PaginationDto
	 * @return collection trip
	 * 
	 */
	public List<Trip> pagination(Client client, SortTripDto sortTripDto, FilterTripClientDto filtrTripClientDto,
			PaginationDto paginationDto) {

		List<Trip> list = mySqlTripDao.paginationDao(client, sortTripDto, filtrTripClientDto, paginationDto);
		return list;
	}

	/**
	 * 
	 * @param object
	 *            SortTripDto ,object FiltrTripClientDto,object client
	 * @return collection trip
	 *
	 */
	public List<Trip> sortTrip(SortTripDto sortTrip, FilterTripClientDto filtrTripClientDto, Client client, int max) {
		List<Trip> list = mySqlTripDao.sortTripClient(sortTrip, filtrTripClientDto, client, max);
		return list;
	}

	/**
	 * 
	 * @param object
	 *            SortTripDto ,object FiltrTripClientDto,object client
	 * @return collection trip
	 * 
	 */
	public List<Trip> filtrTrip(Client client, FilterTripClientDto filtrTripClientDto, SortTripDto sortTripDto) {
		List<Trip> tripsDefine = mySqlTripDao.getFiltrTrips(client, filtrTripClientDto, sortTripDto);
		return tripsDefine;
	}

	/**
	 * method change condition trip
	 * 
	 * @param object
	 *            trip,condition(-1,0,1)
	 *
	 */
	public void changeCondTrip(Trip trip, int condition) {
		trip.setConditionTrip(condition);
		mySqlTripDao.update(trip);

	}

	/**
	 * method fixation trip in database
	 * 
	 * @param object
	 *            trip
	 * 
	 */
	public void fixationTrip(Trip trip) {
		Car car = carServiceImpl.getCar(trip.getCar().getIdCar());
		mySqlTripDao.create(trip);
		car.setCondition(1);
		mySqlCarDao.update(car);
	}

	/**
	 * method delete trip and order in database
	 * 
	 * @param object
	 *            order
	 * 
	 */
	public void deleteTrip(Trip trip) {
		mySqlOrderDao.delete(trip.getIdOrder());

	}

	/**
	 * method define all trip one client
	 * 
	 * @return collection trips
	 * @param object
	 *            client
	 * 
	 */
	public List<Trip> getTripUser(Client client) {
		List<Trip> list = mySqlTripDao.getTripsClient(client);
		return list;
	}

	/**
	 * method get one trip
	 * 
	 * @return object trip
	 * @param id
	 *            trip
	 * 
	 */
	public Trip getTrip(int idTrip) {
		Trip trip = (Trip) mySqlTripDao.get(idTrip);
		return trip;
	}

}
