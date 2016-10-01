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
import by.pvt.dudko.company.dao.impl.ClientDao;
import by.pvt.dudko.company.dao.impl.RoleDao;
import by.pvt.dudko.company.dao.impl.TripDao;
import by.pvt.dudko.company.entities.Car;
import by.pvt.dudko.company.entities.Client;
import by.pvt.dudko.company.entities.Role;
import by.pvt.dudko.company.entities.Trip;
import by.pvt.dudko.company.exception.ServiceException;
import by.pvt.dudko.company.implement.IClientService;
/**
 * ClientServiceImpl class 
 * business logic 
 * @author Aliaksei Dudko
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ClientServiceImpl implements IClientService{
	private static final int CLIENT_ROLE_ID = 2;
	@Autowired
	private IClientDao clientDao;
	@Autowired
	private ITripDao tripDao;
	@Autowired
	@Qualifier("roleDao")
	private IDao roleDao;

	private static final Logger log = Logger.getLogger(ClientServiceImpl.class);

	public ClientServiceImpl() {
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Client> allClient() {
		List<Client> list = clientDao.getAll();
		return list;
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@SuppressWarnings("finally")
	public Client getUser(String login, String password) {
		Client client = null;
		try {
			client = clientDao.getClientByLoginName(login.trim(), password.trim());
		} catch (Exception e) {
			log.error("Error in method servise allClient", e);
		} finally {
			return client;
		}
	}


	public Client registration(String password, String login) {
		Role rol = (Role) roleDao.get(CLIENT_ROLE_ID);
		Client client = new Client(rol, login, password);
		clientDao.create(client);
		return client;
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Trip> tripsClient(Client client) {
		List<Trip> list = tripDao.getTripsClient(client);
		return list;
	}


	public void deleteUser(Client client) {
		clientDao.delete(client.getIdClient());
		log.info("client is delete");
	}


	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Client getClient(int key) {
		Client client = (Client) clientDao.get(key);
		return client;

	}
}
