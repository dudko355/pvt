package by.pvt.dudko.company.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import by.pvt.dudko.company.util.CompanyDateUtil;

/**
 * FilterTripClientDto class 
 * @author Aliaksei Dudko
 *
 */

public class FilterTripClientDto {
	
	private Date dateStart=CompanyDateUtil.date("01/01/1990");
	private Date dateFinish=CompanyDateUtil.date("01/01/2020");
	private String tripTarget="";
	//the first position(with a starting element)
	private final int start=1;
	
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateFinish() {
		return dateFinish;
	}
	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}
	public String getTripTarget() {
		return tripTarget;
	}
	public void setTripTarget(String target) {
		this.tripTarget = target;
	}
	public int getStart() {
		return start;
	}
}
