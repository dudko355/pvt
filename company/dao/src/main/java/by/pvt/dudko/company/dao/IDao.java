package by.pvt.dudko.company.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<T> {
	
	/**
	 * Calls Dao get() method
	 * @param id - id of entity
	 * @return object of derived class Entity
	 * @throws DataAccessException(Spring)
	 */
	T get(Serializable key);

	/**
	 * Calls Dao create() method
	 * @param entity - object of derived class Entity
	 * @throws DataAccessException(Spring)
	 */
	T create(T t);

	/**
	 * Calls Dao update() method
	 * @param entity - object of derived class Entity
	 * @throws DataAccessException(Spring)
	 */
	void update(T t);

	/**
	 * Calls Dao delete() method
     * @param id - id of entity
     * @throws DataAccessException(Spring)
	 */
	void delete(Serializable key);

	/**
	 * Calls Dao all() method
	 * @return all entities of derived class Entity
	 * @throws DataAccessException(Spring)
	 */
	List<T> getAll();

}
