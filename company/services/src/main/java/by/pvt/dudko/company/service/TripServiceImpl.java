package by.pvt.dudko.company.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.pvt.dudko.company.dao.IDao;
import by.pvt.dudko.company.dao.IOrderDao;
import by.pvt.dudko.company.dao.ITripDao;
import by.pvt.dudko.company.dao.impl.MySqlCarDao;
import by.pvt.dudko.company.dao.impl.MySqlOrderDao;
import by.pvt.dudko.company.dao.impl.MySqlTripDao;
import by.pvt.dudko.company.dto.FiltrTripClientDto;
import by.pvt.dudko.company.dto.PaginationDto;
import by.pvt.dudko.company.dto.SortTripDto;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.FiltrTrip;
import by.pvt.dudko.company.entities.Order;
import by.pvt.dudko.company.entities.Pagination;
import by.pvt.dudko.company.entities.SortTrip;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.ServiceException;

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
	public List<Trip> pagination(Client client, SortTripDto sortTripDto, FiltrTripClientDto filtrTripClientDto,
			PaginationDto paginationDto){

		FiltrTrip filtrTrip = null;
		SortTrip sortTrip = null;
		List<Trip> list = null;
		Pagination pagination = new Pagination();
		pagination.setAmount(paginationDto.getCount());
		pagination.setStart(paginationDto.getStart());
		pagination.setAllAmount(paginationDto.getAllCount());
		pagination.setAllPage(paginationDto.getAllPage());
		pagination.setPage(paginationDto.getPage());
		if (filtrTripClientDto != null) {
			filtrTrip = new FiltrTrip();
			filtrTrip.setDateBegin(filtrTripClientDto.getDateBegin());
			filtrTrip.setDateFinish(filtrTripClientDto.getDateFinish());
			filtrTrip.setTarget(filtrTripClientDto.getTarget());
		}
		if (sortTripDto != null) {
			sortTrip = new SortTrip();
			sortTrip.setColumn(sortTripDto.getColumn());
			sortTrip.setOrderColumn(sortTripDto.getOrderColumn());
		}
		list = mySqlTripDao.paginationDao(client, sortTrip, filtrTrip, pagination);
		return list;
	}

	/**
	 * 
	 * @param object
	 *            SortTripDto ,object FiltrTripClientDto,object client
	 * @return collection trip
	 *
	 */
	public List<Trip> sortTrip(SortTripDto sortTripDto, FiltrTripClientDto filtrTripClientDto, Client client,int max)
		{
		FiltrTrip filtrOrder = null;
		List<Trip> list = null;
		if (filtrTripClientDto != null) {
			filtrOrder = new FiltrTrip();
			filtrOrder.setDateBegin(filtrTripClientDto.getDateBegin());
			filtrOrder.setDateFinish(filtrTripClientDto.getDateFinish());
			filtrOrder.setTarget(filtrTripClientDto.getTarget());
		}
		SortTrip sortTrip = new SortTrip();
		sortTrip.setColumn(sortTripDto.getColumn());
		sortTrip.setOrderColumn(sortTripDto.getOrderColumn());
		list = mySqlTripDao.sortTripClient(sortTrip, filtrOrder, client,max);
		return list;
	}

	/**
	 * 
	 * @param object
	 *            SortTripDto ,object FiltrTripClientDto,object client
	 * @return collection trip
	 * 
	 */
	public List<Trip> filtrTrip(Client client, FiltrTripClientDto filtrTripClientDto, SortTripDto sortTripDto)
		{
		List<Trip> tripsDefine = null;
		FiltrTrip filtrTrip = null;
		SortTrip sortTrip = null;
		if (filtrTripClientDto != null) {
			filtrTrip = new FiltrTrip();
			filtrTrip.setDateBegin(filtrTripClientDto.getDateBegin());
			filtrTrip.setDateFinish(filtrTripClientDto.getDateFinish());
			filtrTrip.setTarget(filtrTripClientDto.getTarget());
		}
		if (sortTripDto != null) {
			sortTrip = new SortTrip();
			sortTrip.setColumn(sortTripDto.getColumn());
			sortTrip.setOrderColumn(sortTripDto.getOrderColumn());
		}
		tripsDefine = mySqlTripDao.getFiltrTrips(client, filtrTrip, sortTrip);
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
