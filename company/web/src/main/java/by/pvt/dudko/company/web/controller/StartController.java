package by.pvt.dudko.company.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.pvt.dudko.company.dto.ClientDto;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.web.constant.ConstantsPages;

/**
 * StartController class 
 * @author Aliaksei Dudko
 *
 */
@Controller
public class StartController {
	private static final Logger log = Logger.getLogger(StartController.class);
	public StartController() {    
	
	}
	@RequestMapping(value = "/start_page", method = RequestMethod.GET)
	public String start(){
		return ConstantsPages.PAGES_BASE;
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model){
		model.addAttribute(new ClientDto());
		return ConstantsPages.PAGES_LOGIN;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registrationForm(Model model){
		model.addAttribute(new ClientDto());
		return ConstantsPages.PAGES_REGISTR;
		
	}
	
		
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String adminLocale(){
		return ConstantsPages.LOCALE;
		
	}
	
	@RequestMapping(value = "/locale", method = RequestMethod.GET)
	public String locale(HttpServletRequest request){
		String locale = request.getParameter("locale");
		request.getSession().setAttribute("locale", locale);
		return ConstantsPages.PAGES_BASE;
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
