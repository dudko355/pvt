package by.pvt.dudko.company.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import by.pvt.dudko.company.exception.DateException;
/**
 * CompanyDateUtil class
 * util class for conversion string in date 
 * @author Aliaksei Dudko
 *
 */
public class CompanyDateUtil {
	
	private static final Logger log = Logger.getLogger(CompanyDateUtil.class);
	
	public static Date date(String dateUser){
		log.info("(attempt) change type string on the date(my method)");
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			date=new Date();
			date = sdf.parse(dateUser.trim());
			log.info(" change type string on the date(my method) succesfully");
		} catch (ParseException e) {
			log.error("Error change type string on the date(my method) "+e);
			
		}
		return date;
	}
	
}
