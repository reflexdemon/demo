package demo.dao;

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
	public Customer getCustomer(String id);

	
}
