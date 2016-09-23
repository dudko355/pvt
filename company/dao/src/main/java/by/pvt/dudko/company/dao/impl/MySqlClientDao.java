package by.pvt.dudko.company.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.pvt.dudko.company.dao.BaseDao;
import by.pvt.dudko.company.dao.IClientDao;
import by.pvt.dudko.company.entities.Client;


@Repository
public class MySqlClientDao extends BaseDao<Client> implements IClientDao {

	private static Logger log = Logger.getLogger(MySqlClientDao.class);

	@Autowired
	public MySqlClientDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}


	@SuppressWarnings({ "finally", "unchecked" })
	
	public Client getClientByLoginName(String login, String pass) throws Exception{
		log.info("(attempt) get Client by login and password");
		Client client = null;
		try {
			client = (Client) getSession().createCriteria(Client.class).add(Restrictions.eq("login", login))
					.add(Restrictions.eq("password", pass)).uniqueResult();
			log.info(" get Client successfully:" + client);
		} finally {
			return client;
		}

	}

}
