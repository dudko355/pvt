package by.pvt.dudko.company.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * BaseDao class
 * CRUD operation with POJO  
 * @author Aliaksei Dudko
 *
 */

@Repository
public class BaseDao<T> implements IDao<T> {
	private static Logger log = Logger.getLogger(BaseDao.class);
	private SessionFactory sessionFactory;

	@Autowired

	public BaseDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public T get(Serializable key) {
		log.info("(attempt) Get class by id:" + key + (getPersistentClass()));
		T t = (T) getSession().get(getPersistentClass(), key);
		log.info("get object  " + getPersistentClass() + "  successfully " + t);
		return t;
	}

	public T create(T t) {
		log.info("(attempt) Create object :" + t);
		getSession().save(t);
		log.info("create object  " + getPersistentClass() + "  successfully " + t);
		return t;
	}

	public void update(T t) {
		log.info("(attempt) Update object :" + t);
		getSession().saveOrUpdate(t);
		log.info("update object  " + getPersistentClass() + "  successfully " + t);
	}

	public void delete(Serializable id) {
		log.info("(attempt) Delete object by id:" + id + (getPersistentClass()));
		T t = (T) getSession().get(getPersistentClass(), id);
		getSession().delete(t);
		log.info("delete object  " + getPersistentClass() + "  successfully " + t);
	}

	public List<T> getAll() {
		log.info("(attempt) get all object " + getPersistentClass());
		List<T> t = (List<T>) getSession().createCriteria(getPersistentClass()).list();
		log.info("get all object " + getPersistentClass() + " successfully");
		return t;
	}

	private Class getPersistentClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
