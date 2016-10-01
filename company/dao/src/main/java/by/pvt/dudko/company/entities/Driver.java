package by.pvt.dudko.company.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Driver class
 * POJO Driver 
 * @author Aliaksei Dudko
 *
 */

@Entity
@Table(name="DRIVER")
public class Driver implements Serializable  {
	
	private static final long serialVersionUID = 3L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idDriver")
	private int idDriver;
	@Column(name="name")
	private String name;
	@ManyToMany(cascade={
			CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
	
	@JoinTable(name="DRIVER_CAR",
	joinColumns={@JoinColumn(name="idDriver")},
	inverseJoinColumns={@JoinColumn(name="idCar")})
	private Set<Car> cars=new HashSet();

	public Driver() {
	}

	public Driver(String name, int idDriver) {
		this.name = name;
		this.idDriver = idDriver;
		}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public void setIdDriver(Integer idDriver) {
		this.idDriver = idDriver;
	}

	public String getName() {
		return name;
	}
	
	public Set<Car> getCars() {
		return cars;
	}

	public int getIdDriver() {
		return idDriver;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Driver))
			return false;
		Driver driver = (Driver) o;
		if (idDriver != 0 ? idDriver != driver.idDriver : driver.idDriver != 0)	return false;
		
		return true;
	}

	@Override
	public int hashCode() {
		int result = idDriver != 0 ? new Integer(idDriver).hashCode() : 0;
	return result;
	}
	@Override
	public String toString() {
		return name;
	}

}
