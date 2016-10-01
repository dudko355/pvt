package by.pvt.dudko.company.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Trip class
 * operation with POJO Trip 
 * @author Aliaksei Dudko
 *
 */

@Entity
@Table(name = "TRIP")
public class Trip implements Serializable {

	private static final long serialVersionUID = 6L;

	@Id
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "order"))
	@GeneratedValue(generator = "gen")
	private int idOrder;
	@Column(name = "distance")
	private int distance;
	@Column(name = "tripTarget")
	private String tripTarget;
	@Column(name = "idClient")
	private int idClient;
	@Column(name = "idTrip")
	private int idTrip;
	@Column(name = "dateStart")
	private Date dateStart;
	@Column(name = "dateFinish")
	private Date dateFinish;
	//-1 - the trip hasn't started, 0 - the trip is starting, 1 - the trip ended
	@Column(name = "conditionTrip")
	private int conditionTrip;
	@OneToOne
	@PrimaryKeyJoinColumn
	private Order order;
	@ManyToOne
	@JoinColumn(name="idCar")
	private Car car;

	public Trip() {
	}

	public Trip(String tripTarget, int distance, int idClient, int idTrip, Date dateStart, Date dateFinish,
			int conditionTrip, Car car, Order order) {
		this.tripTarget = tripTarget;
		this.distance = distance;
		this.car = car;
		this.order = order;
		this.idOrder = idOrder;
		this.idClient = idClient;
		this.idTrip = idTrip;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.conditionTrip = conditionTrip;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setConditionTrip(int conditionTrip) {
		this.conditionTrip = conditionTrip;
	}

	public int getConditionTrip() {
		return conditionTrip;
	}

	public void setIdTrip(int idTrip) {
		this.idTrip = idTrip;
	}

	public int getIdTrip() {
		return idTrip;
	}

	public void setTripTarget(String tripTarget) {
		this.tripTarget = tripTarget;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;

	}

	public void setDateFinish(Date dateFinish) {
		this.dateFinish = dateFinish;

	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public Car getCar() {
		return car;
	}

	public Order getOrder() {
		return order;
	}

	public int getIdClient() {
		return idClient;
	}

	public String getTripTarget() {
		return tripTarget;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Trip))
			return false;

		Trip trip = (Trip) o;

		if (idOrder != 0 ? idOrder != trip.idOrder : trip.idOrder != 0)
			return false;
		if (idTrip != 0 ? idTrip != trip.idTrip : trip.idTrip != 0)
			return false;
		if (idClient != 0 ? idClient != trip.idClient : trip.idClient != 0)
			return false;
		if (dateStart != null ? !dateStart.equals(trip.dateStart) : trip.dateStart != null)
			return false;
		if (dateFinish != null ? !dateFinish.equals(trip.dateFinish) : trip.dateFinish != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result0 = idTrip != 0 ? new Integer(idTrip).hashCode() : 0;
		int result1 = idClient != 0 ? new Integer(idClient).hashCode() : 0;
		int result2 = idOrder != 0 ? new Integer(idOrder).hashCode() : 0;
		int result3 = dateStart != null ? dateStart.hashCode() : 0;
		int result4 = dateFinish != null ? dateFinish.hashCode() : 0;
		int result = (result0 + result1 + result2 + result3 + result4) * 7 + 18;
		return result;
	}

	@Override
	public String toString() {
		return " Order : " +order;
	}
}
