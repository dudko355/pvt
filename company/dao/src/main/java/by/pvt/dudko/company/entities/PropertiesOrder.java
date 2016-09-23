package by.pvt.dudko.company.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Parent;


@Embeddable
public class PropertiesOrder implements Serializable {
	private static final long serialVersionUID = 10L;
	private String target;
	private int dictanse;
	private Date dateBegin;
	private Date dateFinish;
	private int seatCount;
	private int mass;
	private int volume;
	 @Parent
	private Order order;
	
	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getSeatCount() {
		return seatCount;
	}
	
	public int getMass() {
		return mass;
	}

	public int getVolume() {
		return volume;
	}

	public String getTarget() {
		return target;
	}

	public int getDictanse() {
		return dictanse;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public Date getDateFinish() {
		return dateFinish;
	}
	public Order getOrder() {
		return order;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	public void setDictanse(int dictanse) {
		this.dictanse = dictanse;
	}

	public void setDateBegin(Date dateBegin) {
		 this.dateBegin= dateBegin;
		 
	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;
		 
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

}
