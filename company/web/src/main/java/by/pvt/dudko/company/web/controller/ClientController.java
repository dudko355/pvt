package by.pvt.dudko.company.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.pvt.dudko.company.dto.ClientDto;
import by.pvt.dudko.company.dto.FilterTripClientDto;
import by.pvt.dudko.company.dto.OrderDto;
import by.pvt.dudko.company.dto.PaginationDto;
import by.pvt.dudko.company.dto.SortTripDto;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Driver;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.DateException;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.implement.ICarService;
import by.pvt.dudko.company.implement.IClientService;
import by.pvt.dudko.company.implement.IOrderService;
import by.pvt.dudko.company.implement.IServiceService;
import by.pvt.dudko.company.implement.ITripService;
import by.pvt.dudko.company.service.CarServiceImpl;
import by.pvt.dudko.company.service.ClientServiceImpl;
import by.pvt.dudko.company.service.OrderServiceImpl;
import by.pvt.dudko.company.service.TripServiceImpl;
import by.pvt.dudko.company.util.CompanyDateUtil;
import by.pvt.dudko.company.web.constant.ConstantsPages;
/**
 * ClientController class 
 * @author Aliaksei Dudko
 *
 */
@Controller
@RequestMapping("/client")
public class ClientController {
	private final int DATE_CORRECT=0;
	private final int CAR_NOT_FOUND=2;
	private static final Logger log = Logger.getLogger(ClientController.class);
	@Autowired
	private IClientService clientServiceImpl;
	@Autowired
	private IServiceService serviceImpl;
	@Autowired
	private ITripService tripServiceImpl;
	@Autowired
	private IOrderService orderServiceImpl;
	@Autowired
	private ICarService carServiceImpl;

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
	public String fixationTrip(Model model,@Valid @ModelAttribute("orderDto") OrderDto orderDto,BindingResult bindingResult, HttpServletRequest request){
		 if(bindingResult.hasErrors()) {
			    model.addAttribute("ERROR", "incorrect");
				model.addAttribute("orderDto", orderDto);
				return ConstantsPages.PAGES_ORDER;
		 }
		Client client = (Client) request.getSession().getAttribute("USER");
		Car car=null;
		int result = orderServiceImpl.estimateDateOrder(orderDto, client);
		if (result == DATE_CORRECT) {
			try {
				car=serviceImpl.transactionSaveTrip(client, orderDto);
			} catch (ServiceException e) {
				model.addAttribute("ERROR", "errorTextNotFoundCar");
				model.addAttribute("URL", "client/order");
				return ConstantsPages.ERROR;
			}
			Set<Driver> drivers=car.getDriver();
			request.getSession().setAttribute("car", car);
			request.getSession().setAttribute("driver", drivers);
			return "redirect:/client/successfully";
		} else if (result == CAR_NOT_FOUND) {
			model.addAttribute("ERROR", "errorTextNotFoundCar");
			model.addAttribute("URL", "client/order");
			return ConstantsPages.ERROR;
		} else {
			model.addAttribute("ERROR", "incorrect");
			model.addAttribute("orderDto", orderDto);
			return ConstantsPages.PAGES_ORDER;
		}
	
	}
	@RequestMapping(value = "/successfully", method = RequestMethod.GET)
	public String orderSuccessfully(Model model){
		return ConstantsPages.CLIENT_ORDER;
		
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String clientDelete(Model model, HttpServletRequest request) {
		Client client = (Client) request.getSession().getAttribute("USER");
		List<Trip> list = tripServiceImpl.getTripUser(client);
		int i = tripServiceImpl.existTripClient(list);
		if (i == 0) {
			clientServiceImpl.deleteUser(client);
			request.getSession().removeAttribute("USER");
			return "redirect:/start_page";
		} else {
			model.addAttribute("ERROR", "warning");
			model.addAttribute("URL", "client/return");
			return ConstantsPages.ERROR;
		}
	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String clientExit(HttpServletRequest request) {
		request.getSession().removeAttribute("USER");
		return ConstantsPages.PAGES_BASE;
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String makeOrder(Model model, HttpServletRequest request) {
		OrderDto orderDto=new OrderDto();
		model.addAttribute(orderDto);
		model.addAttribute("ERROR",null);
		return ConstantsPages.PAGES_ORDER;
	}
	
	@RequestMapping(value = "/redirect/orders", method = RequestMethod.GET)
	public String myOrdersRedirect(HttpServletRequest request){
		Client client = (Client) request.getSession().getAttribute("USER");
		SortTripDto sortTripDto = (SortTripDto) request.getSession().getAttribute("source");
		FilterTripClientDto filtrTripClientDto = (FilterTripClientDto) request.getSession().getAttribute("filtr");
		PaginationDto paginationDto = (PaginationDto) request.getSession().getAttribute("pagination");
		List<Trip> tripsDefine = tripServiceImpl.pagination(client, sortTripDto, filtrTripClientDto, paginationDto);
		request.setAttribute("trips", tripsDefine);
		return ConstantsPages.ORDERS;
		
	}
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String myOrders(Model model, HttpServletRequest request){
		Client client = (Client) request.getSession().getAttribute("USER");
		List<Trip> trips = clientServiceImpl.tripsClient(client);
		PaginationDto paginationDto = new PaginationDto(trips.size(), 1, trips.size(), 1, 0);
		SortTripDto sortTripDto=new SortTripDto("ASC","conditionTrip");
		request.getSession().setAttribute("pagination", paginationDto);
		request.setAttribute("trips", trips);
		request.getSession().setAttribute("filtr", new FilterTripClientDto());
		request.getSession().setAttribute("source",sortTripDto );
		return ConstantsPages.ORDERS;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView allException(Exception e) {
		log.error("error", e);
		ModelAndView model = new ModelAndView("exception_handler");
		model.addObject("ERROR", "errorText");
		return model;

	}
	
	@ExceptionHandler(DataAccessException.class)
	public ModelAndView serviceException(Exception e) {
		log.error("error", e);
		ModelAndView model = new ModelAndView("exception_handler");
		model.addObject("ERROR", "dataBaseError");
		return model;
	}
	
}
