package by.pvt.dudko.company.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Role class
 * POJO Role 
 * @author Aliaksei Dudko
 *
 */


@Entity
@Table(name="ROL")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 5L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idRole")
	private int idRole;
	@Column(name="role")
	private String role;
	@OneToMany(mappedBy="role" ,cascade=CascadeType.ALL)
    private Set<Client> clients;
	
	public Role() {
	}
	
	public Role(String role, int idRole) {
		this.role = role;
		this.idRole = idRole;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	
	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public String getRole() {
		return role;
	}
	
	public Set<Client> getClients() {
		return clients;
	}

	public int getIdRole() {
		return idRole;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Role))
			return false;
		Role rol = (Role) o;
		if (idRole != 0 ? idRole != rol.idRole : rol.idRole != 0)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = idRole != 0 ? new Integer(idRole).hashCode() : 0;
	return result;
	}

	@Override
	public String toString() {
		return "Rol : id: " + idRole + " : " + role;
	}
}
