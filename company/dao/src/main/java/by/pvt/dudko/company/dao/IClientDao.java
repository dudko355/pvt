package by.pvt.dudko.company.dao;


import by.pvt.dudko.company.entities.Client;

public interface IClientDao extends IDao<Client>{
	/**
	 * 
	 * @param login(String) and password(String)
	 * @return object Client
	 * @throws DataAccessException(Spring)
	 */
	public Client getClientByLoginName(String login, String pass) throws Exception;
}
