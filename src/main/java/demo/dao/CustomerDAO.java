package demo.dao;

import java.util.List;

import demo.domain.Customer;

/**
 * The Interface CustomerDAO.
 */
public interface CustomerDAO extends GenericDAO {
	

	/**
	 * Gets the customer.
	 *
	 * @param id the id
	 * @return the customer
	 */
	public Customer findById(String id);

	/**
	 * Find all.
	 *
	 * @return the customer
	 */
	public List<Customer> findAll();
	
}
