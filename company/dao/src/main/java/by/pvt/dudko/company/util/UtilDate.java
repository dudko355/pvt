package by.pvt.dudko.company.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
@Component
public class UtilDate {
	
	private static final Logger log = Logger.getLogger(UtilDate.class);
	
	public static Date date(String date) {
		log.info("(attempt) change type string on the date(my method)");
		Date Date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date = sdf.parse(date.trim());
			log.info(" change type string on the date(my method) succesfully");
		} catch (ParseException e) {
			log.error("Error change type string on the date(my method) "+e);
		}
		return Date;
	}
	
}
