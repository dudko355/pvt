package by.pvt.dudko.company.implement;

import java.util.List;

import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Trip;

public interface IClientService {

	/**
	 * all client
	 * 
	 * @return collection all driver
	 * 
	 */
	public List<Client> allClient() ;
	
	/**
	 * method define client
	 * 
	 * @param login
	 *            and password
	 * @return client if he exist,else null
	 * 
	 */
	public Client getUser(String login, String password);
	
	
	/**
	 * method writing new client in database
	 * 
	 * @param login
	 *            and password
	 * @return this client
	 * 
	 */
	public Client registration(String password, String login) ;
	
	/**
	 * method define all trips one client
	 * 
	 * @param object
	 *            client
	 * @return collection trips
	 *
	 */
	public List<Trip> tripsClient(Client client) ;
	
	/**
	 * method delete user(client)
	 * 
	 * @param object
	 *            client
	 * 
	 */
	public void deleteUser(Client client);
	
	/**
	 * @param key
	 *            - id of Client
	 * @return entity Client
	 * 
	 */
	public Client getClient(int key) ;
	
}
