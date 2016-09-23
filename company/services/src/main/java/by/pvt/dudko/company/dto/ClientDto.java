package by.pvt.dudko.company.dto;

import javax.persistence.Column;

public class ClientDto {
	
	private String password;
	private String login;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
}
