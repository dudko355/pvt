package by.pvt.dudko.company.web.controller;

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

import by.pvt.dudko.company.dto.FilterTripClientDto;
import by.pvt.dudko.company.dto.PaginationDto;
import by.pvt.dudko.company.dto.SortTripDto;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.DateException;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.implement.ITripService;
import by.pvt.dudko.company.service.TripServiceImpl;
import by.pvt.dudko.company.util.CompanyDateUtil;
import by.pvt.dudko.company.web.constant.ConstantsPages;
/**
 * TripController class 
 * operation on the client page 
 * @author Aliaksei Dudko
 *
 */
@Controller
@RequestMapping("/trip")
public class TripController {
	private static final Logger log = Logger.getLogger(TripController.class);

	@Autowired
	private ITripService tripServiceImpl;

	@RequestMapping(value = "/filtr", method = RequestMethod.POST)
	public String filtrTrip(Model model, HttpServletRequest request) throws Exception{
		FilterTripClientDto filtrTripClientDto = new FilterTripClientDto();
		String dateStart = request.getParameter("dateStart").trim();
		String dateFinish = request.getParameter("dateFinish").trim();
		if (!dateStart.equals("")) {
			filtrTripClientDto.setDateStart(CompanyDateUtil.date(dateStart));
		}
		if (!dateFinish.equals("")) {
			filtrTripClientDto.setDateFinish(CompanyDateUtil.date(dateFinish));
		}
		filtrTripClientDto.setTripTarget(request.getParameter("target").trim());
		Client client = (Client) request.getSession().getAttribute("USER");
		SortTripDto sortTripDto = (SortTripDto) request.getSession().getAttribute("source");
		List<Trip> tripsDefine = tripServiceImpl.filterTrip(client, filtrTripClientDto, sortTripDto);
		PaginationDto paginationDto = new PaginationDto(tripsDefine.size(), 1, tripsDefine.size(), 1, 0);
		request.getSession().setAttribute("filtr", filtrTripClientDto);
		request.getSession().setAttribute("pagination", paginationDto);
		request.setAttribute("trips", tripsDefine);
		return ConstantsPages.ORDERS;
	}

	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public String sortTrip(Model model, HttpServletRequest request) {
		SortTripDto sortTripDto = new SortTripDto();
		sortTripDto.setOrderColumn(request.getParameter("order").trim());
		sortTripDto.setColumn(request.getParameter("source").trim());
		FilterTripClientDto filtrTripClientDto = (FilterTripClientDto) request.getSession().getAttribute("filtr");
		Client client = (Client) request.getSession().getAttribute("USER");
		PaginationDto paginationDto=(PaginationDto) request.getSession().getAttribute("pagination");
		List<Trip> nextTrips = tripServiceImpl.sortTrip(sortTripDto, filtrTripClientDto, client,paginationDto.getCount());
		request.setAttribute("trips", nextTrips);
		request.getSession().setAttribute("source", sortTripDto);
		return ConstantsPages.ORDERS;
	}

	@RequestMapping(value = "/amount", method = RequestMethod.GET)
	public String amountElementPage(Model model, HttpServletRequest request){
		int amount = Integer.parseInt(request.getParameter("element").trim());
		Client client = (Client) request.getSession().getAttribute("USER");
		SortTripDto sortTripDto = (SortTripDto) request.getSession().getAttribute("source");
		FilterTripClientDto filtrTripClientDto = (FilterTripClientDto) request.getSession().getAttribute("filtr");
		PaginationDto paginationDto = (PaginationDto) request.getSession().getAttribute("pagination");
		int allPage = tripServiceImpl.amountPages(paginationDto, amount);
		PaginationDto paginationDtoTwo = new PaginationDto(paginationDto.getAllCount(), allPage, amount, 1, 0);
		List<Trip> tripsDefine = tripServiceImpl.pagination(client, sortTripDto, filtrTripClientDto, paginationDtoTwo);
		request.setAttribute("trips", tripsDefine);
		request.getSession().setAttribute("pagination", paginationDtoTwo);
		return ConstantsPages.ORDERS;

	}

	@RequestMapping(value = "/next_page", method = RequestMethod.GET)
	public String nextPage(Model model, HttpServletRequest request) {
		int number = Integer.parseInt(request.getParameter("next_page").trim());
		PaginationDto paginationDto = (PaginationDto) request.getSession().getAttribute("pagination");
		Client client = (Client) request.getSession().getAttribute("USER");
		FilterTripClientDto filtrTripClientDto = (FilterTripClientDto) request.getSession().getAttribute("filtr");
		SortTripDto sortTripDto = (SortTripDto) request.getSession().getAttribute("source");
		paginationDto = tripServiceImpl.nextPage(paginationDto, number);
		List<Trip> tripsDefine = tripServiceImpl.pagination(client, sortTripDto, filtrTripClientDto, paginationDto);
		request.getSession().setAttribute("pagination", paginationDto);
		request.setAttribute("trips", tripsDefine);
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
