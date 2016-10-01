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
		return tripDao.getAll();
	}

	public int amountPages(PaginationDto paginationDto, int amount) {

		if (paginationDto.getAllCount() % amount == 0) {
			return paginationDto.getAllCount() / amount;
		} else {
			return (int) paginationDto.getAllCount() / amount + 1;
		}
	}

	public PaginationDto nextPage(PaginationDto paginationDto, int number) {
		//the first position(with a starting element)
		int start = 0;
		if (number == 1) {
			start = paginationDto.getStart() + paginationDto.getCount();
			paginationDto.setStart(start);
			paginationDto.setPage(paginationDto.getPage() + 1);
		} else {
			start = paginationDto.getStart() - paginationDto.getCount();
			paginationDto.setStart(start);
			paginationDto.setPage(paginationDto.getPage() - 1);
		}
		return paginationDto;

	}

	public int existTripClient(List<Trip> trips) {
		int statusEndedTrip=1;
		int countTripNotStarted = 0;
		for (Trip trip : trips) {
			if (trip.getConditionTrip() == statusEndedTrip) {
				deleteTrip(trip);
			} else {
				countTripNotStarted = countTripNotStarted + 1;
			}
		}
		return countTripNotStarted;

	}

	public List<Trip> pagination(Client client, SortTripDto sortTripDto, FilterTripClientDto filtrTripClientDto,
			PaginationDto paginationDto) {
		return tripDao.paginationDao(client, sortTripDto, filtrTripClientDto, paginationDto);
	}


	public List<Trip> sortTrip(SortTripDto sortTrip, FilterTripClientDto filtrTripClientDto, Client client, int max) {
		return tripDao.sortTripClient(sortTrip, filtrTripClientDto, client, max);
	}


	public List<Trip> filterTrip(Client client, FilterTripClientDto filtrTripClientDto, SortTripDto sortTripDto) {
		return tripDao.getFiltrTrips(client, filtrTripClientDto, sortTripDto);
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
		return tripDao.getTripsClient(client);
	}


	public Trip getTrip(int idTrip) {
		return (Trip) tripDao.get(idTrip);
	}

}
