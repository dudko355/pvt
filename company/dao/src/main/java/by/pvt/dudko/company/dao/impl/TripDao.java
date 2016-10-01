package by.pvt.dudko.company.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import by.pvt.dudko.company.dao.BaseDao;
import by.pvt.dudko.company.dao.IOrderDao;
import by.pvt.dudko.company.dao.ITripDao;
import by.pvt.dudko.company.dto.FilterTripClientDto;
import by.pvt.dudko.company.dto.PaginationDto;
import by.pvt.dudko.company.dto.SortTripDto;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Trip;

/**
 * TripDao class
 * operation with POJO Trip 
 * @author Aliaksei Dudko
 *
 */

@Repository
public class TripDao extends BaseDao<Trip> implements ITripDao{

	private static Logger log = Logger.getLogger(TripDao.class);

	@Autowired
	public TripDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Trip> getTripsClient(Client client) {
		log.info("(attempt) get trips client " + client);
		List<Trip> trips = criteriaStartClientTrip(client).list();
		log.info(" get trips successfully");
		return trips;
	}

	public List<Trip> sortTripClient(SortTripDto sortOrder, FilterTripClientDto filtrOrder, Client client,int max) {
		Criteria criteria = null;
		if (filtrOrder == null) {
			criteria = criteriaOrder(sortOrder, client);
		} else if (!filtrOrder.getTripTarget().equals("")) {
			criteria = criteriaOrderDate(filtrOrder, criteriaOrder(sortOrder, client));
			criteria.add(Restrictions.eq("tripTarget", filtrOrder.getTripTarget()));
		} else if (filtrOrder.getTripTarget().equals("")) {
			criteria = criteriaOrderDate(filtrOrder, criteriaOrder(sortOrder, client));
		}
		criteria.setMaxResults(max);
		List<Trip> trips = criteria.list();
		return trips;
	}

	private Criteria criteriaOrder(SortTripDto sortOrder, Client client) {
		Criteria criteria = criteriaStartClientTrip(client);
		criteriaSort(criteria, sortOrder);
		return criteria;
	}

	private Criteria criteriaSort(Criteria criteria, SortTripDto sortOrder) {
		if (sortOrder.getOrderColumn().equals("ASC")) {
			criteria.addOrder(Order.asc(sortOrder.getColumn()));
		} else {
			criteria.addOrder(Order.desc(sortOrder.getColumn()));
		}
		return criteria;
	}

	private Criteria criteriaStartClientTrip(Client client) {
		Criteria criteria = getSession().createCriteria(Trip.class);
		return criteria.add(Restrictions.eq("idClient", client.getIdClient()));
	}

	private Criteria criteriaStartClientTripAmountNext(PaginationDto pagination, Client client) {
		Criteria criteria = criteriaStartClientTrip(client);
		criteria.setFirstResult(pagination.getStart());
		criteria.setMaxResults(pagination.getCount());
		return criteria;

	}

	private Criteria criteriaOrderDate(FilterTripClientDto filtrOrder, Criteria criteria) {
		criteria.add(Restrictions.gt("dateStart", filtrOrder.getDateStart()));
		criteria.add(Restrictions.lt("dateFinish", filtrOrder.getDateFinish()));
		return criteria;
	}


	public List<Trip> paginationDao(Client client, SortTripDto sortTrip, FilterTripClientDto filtrOrder, PaginationDto pagination) {
		Criteria criteria = criteriaStartClientTripAmountNext(pagination, client);
		if (sortTrip != null) {
			criteria = criteriaSort(criteria, sortTrip);
		}
		if (filtrOrder != null) {
			criteria = criteriaOrderDate(filtrOrder, criteria);
			if (!filtrOrder.getTripTarget().equals("")) {
				criteria.add(Restrictions.eq("tripTarget", filtrOrder.getTripTarget()));
			}
		}
		List<Trip> trips = criteria.list();
		log.info(" get trips pagination successfully");
		return trips;
	}


	public List<Trip> getFiltrTrips(Client client, FilterTripClientDto filtrTrip, SortTripDto sortTrip) {
		Criteria criteria = criteriaStartClientTrip(client);
		criteria = criteriaOrderDate(filtrTrip, criteria);
		if (!filtrTrip.getTripTarget().equals("")) {
			criteria.add(Restrictions.eq("tripTarget", filtrTrip.getTripTarget()));
		}
		if (sortTrip != null) {
			criteria = criteriaSort(criteria, sortTrip);
		}
		List<Trip> trips = criteria.list();
		return trips;
	}

}
