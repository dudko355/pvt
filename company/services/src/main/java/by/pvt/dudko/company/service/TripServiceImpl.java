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
import by.pvt.dudko.company.implement.ICarService;
import by.pvt.dudko.company.implement.ITripService;
/**
 * TripServiceImpl class 
 * business logic 
 * @author Aliaksei Dudko
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TripServiceImpl  implements ITripService {

	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private ITripDao tripDao;
	@Autowired
	@Qualifier("carDao")
	private IDao carDao;
	@Autowired
	private ICarService carServiceImpl;
	private static final Logger log = Logger.getLogger(TripServiceImpl.class);

	public TripServiceImpl(){
	}


	public List<Trip> allTrip() {
		List<Trip> list = tripDao.getAll();
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

		List<Trip> list = tripDao.paginationDao(client, sortTripDto, filtrTripClientDto, paginationDto);
		return list;
	}


	public List<Trip> sortTrip(SortTripDto sortTrip, FilterTripClientDto filtrTripClientDto, Client client, int max) {
		List<Trip> list = tripDao.sortTripClient(sortTrip, filtrTripClientDto, client, max);
		return list;
	}


	public List<Trip> filtrTrip(Client client, FilterTripClientDto filtrTripClientDto, SortTripDto sortTripDto) {
		List<Trip> tripsDefine = tripDao.getFiltrTrips(client, filtrTripClientDto, sortTripDto);
		return tripsDefine;
	}


	public void changeCondTrip(Trip trip, int condition) {
		trip.setConditionTrip(condition);
		tripDao.update(trip);

	}


	public void fixationTrip(Trip trip) {
		Car car = carServiceImpl.getCar(trip.getCar().getIdCar());
		tripDao.create(trip);
		car.setCondition(1);
		carDao.update(car);
	}


	public void deleteTrip(Trip trip) {
		orderDao.delete(trip.getIdOrder());

	}


	public List<Trip> getTripUser(Client client) {
		List<Trip> list = tripDao.getTripsClient(client);
		return list;
	}


	public Trip getTrip(int idTrip) {
		Trip trip = (Trip) tripDao.get(idTrip);
		return trip;
	}

}
