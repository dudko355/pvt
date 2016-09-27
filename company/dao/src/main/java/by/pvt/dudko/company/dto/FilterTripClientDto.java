package by.pvt.dudko.company.dto;

import java.util.Date;

import by.pvt.dudko.company.util.UtilDate;



public class FilterTripClientDto {
	
	private Date dateBegin=UtilDate.date("01/01/1990");
	private Date dateFinish=UtilDate.date("01/01/2020");
	private String target="";
	private final int start=1;
	
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	public Date getDateFinish() {
		return dateFinish;
	}
	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getStart() {
		return start;
	}
}
