package by.pvt.dudko.company.dto;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
/**
 * OrderDto class 
 * @author Aliaksei Dudko
 *
 */
public class OrderDto {
	
	private String orderTarget;
	private int distance;
	@NotEmpty(message="wrong date.")
	private String dateStart;
	@NotEmpty(message="wrong date.")
	private String dateFinish;
	private int seatCount;
	private int mass;
	private int volume;
	
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

	public String getDateStart() {
		return dateStart;
	}

	public String getDateFinish() {
		return dateFinish;
	}
	
	public void setOrderTarget(String orderTarget) {
		this.orderTarget = orderTarget;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setDateStart(String dateStart) {
		 this.dateStart= dateStart;
		 
	}

	public void setDateFinish(String dateFinish) {
		this.dateFinish = dateFinish;
		 
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public void setMass(int mass) {
		this.mass = mass;
	}

}
