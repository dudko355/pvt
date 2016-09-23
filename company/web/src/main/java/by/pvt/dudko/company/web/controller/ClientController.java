package by.pvt.dudko.company.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import by.pvt.dudko.company.dto.FiltrTripClientDto;
import by.pvt.dudko.company.dto.OrderDto;
import by.pvt.dudko.company.dto.PaginationDto;
import by.pvt.dudko.company.dto.SortTripDto;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.service.CarServiceImpl;
import by.pvt.dudko.company.service.ClientServiceImpl;
import by.pvt.dudko.company.service.OrderServiceImpl;
import by.pvt.dudko.company.service.TripServiceImpl;
import by.pvt.dudko.company.util.UtilDate;
import by.pvt.dudko.company.web.impl.constant.ConstantsPages;

@Controller
@RequestMapping("/client")
public class ClientController {
	private static final Logger log = Logger.getLogger(ClientController.class);
	@Autowired
	private ClientServiceImpl clientServiceImpl;
	@Autowired
	private TripServiceImpl tripServiceImpl;
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	@Autowired
	private CarServiceImpl carServiceImpl;

	@RequestMapping(value = "/return", method = RequestMethod.GET)
	public String pageClient() {
		return ConstantsPages.PAGES_CLIENT;
	}

	@RequestMapping(value = "/delTrip", method = RequestMethod.POST)
	public String deleteOneTripClient(Model model, HttpServletRequest request) {
		int idOrder = Integer.parseInt((request.getParameter("delete_trip")));
		Trip trip = tripServiceImpl.getTrip(idOrder);
		tripServiceImpl.deleteTrip(trip);
		List<Trip> trips = clientServiceImpl.tripsClient((Client) request.getSession().getAttribute("USER"));
		model.addAttribute("trips", trips);
		return "redirect:/client/redirect/orders";

	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String fixationTrip(Model model, OrderDto orderDto, HttpServletRequest request) {
		String page = null;
		Client client = (Client) request.getSession().getAttribute("USER");
		int i = orderServiceImpl.estimateDateOrder(orderDto, client);
		if (i == 0) {
			return "redirect:/client/return";
		} else if (i == 2) {
			model.addAttribute("ERROR", "errorTextNotFoundCar");
			model.addAttribute("URL", "client/order");
			return ConstantsPages.ERROR;
		} else {
			model.addAttribute("ERROR", "incorrect");
			model.addAttribute("URL", "client/order");
			return ConstantsPages.ERROR;
		}
	
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String clientDelete(Model model, HttpServletRequest request) {
		Client client = (Client) request.getSession().getAttribute("USER");
		List<Trip> list = tripServiceImpl.getTripUser(client);
		int i = tripServiceImpl.existTripClient(list);
		if (i == 0) {
			clientServiceImpl.deleteUser(client);
			request.getSession().invalidate();
			return "redirect:/start_page";
		} else {
			model.addAttribute("ERROR", "warning");
			model.addAttribute("URL", "client/return");
			return ConstantsPages.ERROR;
		}
	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String clientExit(HttpServletRequest request) {
		request.getSession().invalidate();
		return ConstantsPages.PAGES_BASE;
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String makeOrder(Model model, HttpServletRequest request) {
		model.addAttribute(new OrderDto());
		return ConstantsPages.PAGES_ORDER;
	}
	
	@RequestMapping(value = "/redirect/orders", method = RequestMethod.GET)
	public String myOrdersRedirect(HttpServletRequest request){
		String page = null;
		Client client = (Client) request.getSession().getAttribute("USER");
		SortTripDto sortTripDto = (SortTripDto) request.getSession().getAttribute("source");
		FiltrTripClientDto filtrTripClientDto = (FiltrTripClientDto) request.getSession().getAttribute("filtr");
		PaginationDto paginationDto = (PaginationDto) request.getSession().getAttribute("pagination");
		List<Trip> tripsDefine = tripServiceImpl.pagination(client, sortTripDto, filtrTripClientDto, paginationDto);
		request.setAttribute("trips", tripsDefine);
		return page = ConstantsPages.ORDERS;
		
	}
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String myOrders(Model model, HttpServletRequest request){
		String page = null;
		Client client = (Client) request.getSession().getAttribute("USER");
		List<Trip> trips = clientServiceImpl.tripsClient(client);
		PaginationDto paginationDto = new PaginationDto(trips.size(), 1, trips.size(), 1, 0);
		SortTripDto sortTripDto=new SortTripDto("ASC","conditionTrip");
		request.getSession().setAttribute("pagination", paginationDto);
		request.setAttribute("trips", trips);
		request.getSession().setAttribute("filtr", new FiltrTripClientDto());
		request.getSession().setAttribute("source",sortTripDto );
		page = ConstantsPages.ORDERS;
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
