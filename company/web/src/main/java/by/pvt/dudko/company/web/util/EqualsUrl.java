package by.pvt.dudko.company.web.util;

import javax.servlet.http.HttpServletRequest;
/**
 * EqualsUrl class 
 * contain permitted URL in Array
 * @author Aliaksei Dudko
 *
 */
public class EqualsUrl {
public static String[] urlEquals(HttpServletRequest request){
	String path = request.getContextPath();
	String indexURI = path + "/index";
	String startURI = path + "/start_page";
	String loginURI = path + "/login";
	String registrationURI = path + "/registration";
	String errorURI = path + "/error";
	String adminURI = path + "/admin/admin";
	String localeURI = path + "/locale";
	String pictureURI = path + "/resources/fon.jpg";
	String[] url={indexURI,startURI,loginURI,registrationURI,errorURI,adminURI,localeURI,pictureURI};
	return url;
}
}
