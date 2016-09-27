package by.pvt.dudko.company.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.service.ClientServiceImpl;
import by.pvt.dudko.company.web.constant.ConstantsPages;

@Controller
@RequestMapping("/user")
public class EnterClientController {
	private static final Logger log = Logger.getLogger(EnterClientController.class);
	@Autowired
	private ClientServiceImpl clientServiceImpl;

	@RequestMapping(value = "/enter", method = RequestMethod.POST)
	public String loginEnter(ClientDto clientDto, Model model, HttpServletRequest request) {
		String page = null;
		Client client = clientServiceImpl.getUser(clientDto.getLogin(), clientDto.getPassword());
		if (!(client == null)) {
			page = ConstantsPages.PAGES_CLIENT;
			request.getSession().setAttribute("USER", client);
		} else {
			page = ConstantsPages.ERROR;
			model.addAttribute("ERROR", "clientEnterError");
			model.addAttribute("URL", "login");
		}
		return page;
	}

	@RequestMapping(value = "/registr", method = RequestMethod.POST)
	public String registration(ClientDto clientDto, Model model, HttpServletRequest request) {
		String page = null;
		Client client = clientServiceImpl.getUser(clientDto.getLogin(), clientDto.getPassword());
		if (!clientDto.getLogin().isEmpty() && !clientDto.getLogin().isEmpty() && client == null) {
			client = clientServiceImpl.registration(clientDto.getPassword(), clientDto.getLogin());
			request.getSession().setAttribute("USER", client);
			page = ConstantsPages.PAGES_CLIENT;
		} else {
			page = ConstantsPages.ERROR;
			model.addAttribute("ERROR", "clientRegistrError");
			model.addAttribute("URL", "registration");
		}

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
