package by.pvt.dudko.company.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import by.pvt.dudko.company.dao.IClientDao;
import by.pvt.dudko.company.dao.IDao;
import by.pvt.dudko.company.dao.ITripDao;
import by.pvt.dudko.company.dao.impl.MySqlClientDao;
import by.pvt.dudko.company.dao.impl.MySqlRolDao;
import by.pvt.dudko.company.dao.impl.MySqlTripDao;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Role;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.ServiceException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ClientServiceImpl {
	private static final int CLIENT_ROLE_ID = 2;
	@Autowired
	private IClientDao mySqlClientDao;
	@Autowired
	private ITripDao mySqlTripDao;
	@Autowired
	@Qualifier("mySqlRolDao")
	private IDao mySqlRolDao;

	private static final Logger log = Logger.getLogger(ClientServiceImpl.class);

	public ClientServiceImpl() {
	}

	/**
	 * all client
	 * 
	 * @return collection all driver
	 * 
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Client> allClient() {
		List<Client> list = mySqlClientDao.getAll();
		return list;
	}

	/**
	 * method define client
	 * 
	 * @param login
	 *            and password
	 * @return client if he exist,else null
	 * 
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@SuppressWarnings("finally")
	public Client getUser(String login, String password) {
		Client client = null;
		try {
			client = mySqlClientDao.getClientByLoginName(login.trim(), password.trim());
		} catch (Exception e) {
			log.error("Error in method servise allClient", e);
		} finally {
			return client;
		}
	}

	/**
	 * method writing new client in database
	 * 
	 * @param login
	 *            and password
	 * @return this client
	 * 
	 */
	public Client registration(String password, String login) {
		Role rol = (Role) mySqlRolDao.get(CLIENT_ROLE_ID);
		Client client = new Client(rol, login, password);
		mySqlClientDao.create(client);
		return client;
	}

	/**
	 * method define all trips one client
	 * 
	 * @param object
	 *            client
	 * @return collection trips
	 *
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Trip> tripsClient(Client client) {
		List<Trip> list = mySqlTripDao.getTripsClient(client);
		return list;
	}

	/**
	 * method delete user(client)
	 * 
	 * @param object
	 *            client
	 * 
	 */
	public void deleteUser(Client client) {
		mySqlClientDao.delete(client.getIdClient());
		log.info("client is delete");
	}

	/**
	 * @param key
	 *            - id of Client
	 * @return entity Client
	 * 
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Client getClient(int key) {
		Client client = (Client) mySqlClientDao.get(key);
		return client;

	}
}
