package by.pvt.dudko.company.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Parent;
/**
 * Component for POJO Order 
 * @author Aliaksei Dudko
 *
 */

@Embeddable
public class PropertiesOrder implements Serializable {
	private static final long serialVersionUID = 10L;
	private String orderTarget;
	private int distance;
	private Date dateStart;
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

	public String getOrderTarget() {
		return orderTarget;
	}

	public int getDistance() {
		return distance;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public Date getDateFinish() {
		return dateFinish;
	}
	public Order getOrder() {
		return order;
	}
	
	public void setOrderTarget(String orderTarget) {
		this.orderTarget = orderTarget;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setDateStart(Date dateStart) {
		 this.dateStart= dateStart;
		 
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
