package by.pvt.dudko.company.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Order class POJO Order
 * 
 * @author Aliaksei Dudko
 *
 */

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

	private static final long serialVersionUID = 4L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idOrder")
	private int idOrder;
	@Embedded
	private PropertiesOrder propertiesOrder;
	@ManyToOne
	@JoinColumn(name = "idClient")
	private Client client;
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Trip trip;

	public Order() {
	}

	public Order(Client client, PropertiesOrder propertiesOrder) {

		this.idOrder = idOrder;
		this.client = client;
		this.propertiesOrder = propertiesOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public void setPropertiesOrder(PropertiesOrder propertiesOrder) {
		this.propertiesOrder = propertiesOrder;
	}

	public PropertiesOrder getPropertiesOrder() {
		return propertiesOrder;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public Client getClient() {
		return client;
	}

	public Trip getTrip() {
		return trip;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Order))
			return false;

		Order order = (Order) o;

		if (idOrder != 0 ? idOrder != order.idOrder : order.idOrder != 0)
			return false;
		if (client != null ? client != order.getClient() : order.getClient() != null)
			return false;
		if (propertiesOrder != null ? propertiesOrder != order.getPropertiesOrder()
				: order.getPropertiesOrder() != null)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return (client.hashCode() + new Integer(idOrder).hashCode() + propertiesOrder.hashCode()) * 7 + 18;

	}

	@Override
	public String toString() {
		return "Client : " + client + " Order : id: " + idOrder + " Target: " + propertiesOrder.getOrderTarget()
				+ " Date start: " + propertiesOrder.getDateStart() + " Date finish: " + propertiesOrder.getDateFinish();
	}

}