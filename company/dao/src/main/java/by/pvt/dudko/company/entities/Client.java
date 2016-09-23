package by.pvt.dudko.company.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="USER")
public class Client implements Serializable  {
	
	private static final long serialVersionUID = 2L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idClient")
	private int idClient;
	@Column(name="password")
	private String password;
	@Column(name="login")
	private String login;
	@ManyToOne
	@JoinColumn(name="rol")
	private Role rol;
	@OneToMany(mappedBy="client")	
	private Set<Order> orders;

	public Client(){}
	
	public Client(Role rol, String login, String password) {
		this.idClient = idClient;
		this.rol = rol;
		this.login = login;
		this.password = password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public Role getRol() {
		return rol;
	}
	
	public Set<Order> getOrders() {
		return orders;
	}

	public int getIdClient() {
		return idClient;
	}

	public String getLogin() {
		return login;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Client))
			return false;
		Client client = (Client) o;
		if (idClient != 0 ? idClient != client.idClient : client.idClient != 0)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = idClient != 0 ? new Integer(idClient).hashCode() : 0;
	return result;
	}

	@Override
	public String toString() {
		return "Client : id: " + idClient + " Name: " + login+" , Rol : "+rol;
	}
}
