package by.pvt.dudko.company.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.pvt.dudko.company.dto.ClientDto;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.service.AdminServiceImpl;
import by.pvt.dudko.company.service.CarServiceImpl;
import by.pvt.dudko.company.service.ClientServiceImpl;
import by.pvt.dudko.company.service.DriverServerImpl;
import by.pvt.dudko.company.service.OrderServiceImpl;
import by.pvt.dudko.company.service.TripServiceImpl;
import by.pvt.dudko.company.web.constant.ConstantsPages;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final int ADMIN_ROLE_ID = 1;
	private static final Logger log = Logger.getLogger(AdminController.class);
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	@Autowired
	private DriverServerImpl driverServerImpl;
	@Autowired
	private CarServiceImpl carServiceImpl;
	@Autowired
	private ClientServiceImpl clientServiceImpl;
	@Autowired
	private TripServiceImpl tripServiceImpl;

	public AdminController() {
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPages(Model model, HttpServletRequest request){
		String page = null;
		Client admin = clientServiceImpl.getClient(ADMIN_ROLE_ID);
		page = ConstantsPages.PAGES_ADMIN;
		request.getSession().setAttribute("USER", admin);
		return page;
	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String adminExit(HttpServletRequest request) {
		request.getSession().invalidate();
		return ConstantsPages.PAGES_BASE;
	}

	@RequestMapping(value = "/exit_inf", method = RequestMethod.GET)
	public String adminExitInf() {
		return ConstantsPages.PAGES_ADMIN;
	}

	@RequestMapping(value = "/trip", method = RequestMethod.GET)
	public String adminTripInf(Model model){
		String page = null;
		List<Trip> trips = tripServiceImpl.allTrip();
		model.addAttribute("ALLTRIP", trips);
		page = ConstantsPages.CHANGE_TRIP;
		return page;
	}

	@RequestMapping(value = "/trip_cond", method = RequestMethod.POST)
	public String changeTripCondition(Model model, HttpServletRequest request){
		String page = null;
		int idOrder = Integer.parseInt((request.getParameter("change_condition")));
		int newCond = Integer.parseInt((request.getParameter("condition")).trim());
		Trip trip = tripServiceImpl.getTrip(idOrder);
		tripServiceImpl.changeCondTrip(trip, newCond);
		List<Trip> trips = tripServiceImpl.allTrip();
		model.addAttribute("ALLTRIP", trips);
		page = ConstantsPages.CHANGE_TRIP;
		return page;
	}

	@RequestMapping(value = "/car", method = RequestMethod.GET)
	public String adminCarInf(Model model) {
		String page = null;
		model.addAttribute("ALLCAR", carServiceImpl.allCar());
		page = ConstantsPages.CHANGE_CAR_COND;
		return page;
	}

	@RequestMapping(value = "/car_cond", method = RequestMethod.POST)
	public String changeCarCondition(Model model, HttpServletRequest request){
		String page = null;
		int idCar = Integer.parseInt((request.getParameter("change_condition")));
		int newCond = Integer.parseInt((request.getParameter("condition")).trim());
		Car car = carServiceImpl.getCar(idCar);
		carServiceImpl.changeConditionCar(car, newCond);
		model.addAttribute("ALLCAR", carServiceImpl.allCar());
		page = ConstantsPages.CHANGE_CAR_COND;
		return page;
	}


	@RequestMapping(value = "/inf", method = RequestMethod.GET)
	public String adminInf(Model model, HttpServletRequest request) {
		String page = null;
		model.addAttribute("CARS", adminServiceImpl.brokenCars());
		model.addAttribute("DRIVER", driverServerImpl.allDriver());
		model.addAttribute("ALLTRIP", tripServiceImpl.allTrip());
		List<Car> list = adminServiceImpl.busyCar();
		model.addAttribute("CARTRIP", list);
		page = ConstantsPages.ADMIN_INF;
		return page;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView allException(Exception e) {
		log.error("error", e);
		ModelAndView model = new ModelAndView("error_two");
		model.addObject("ERROR", "errorText");
		return model;

	}
	
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView serviceException(Exception e) {
		log.error("error", e);
		ModelAndView model = new ModelAndView("error_two");
		model.addObject("ERROR", "dataBaseError");
		
		return model;
	}
}
