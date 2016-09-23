package by.pvt.dudko.company.entities;

import java.util.Date;

public class FiltrTrip {
	private Date dateBegin;
	private Date dateFinish;
	private String target;
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
