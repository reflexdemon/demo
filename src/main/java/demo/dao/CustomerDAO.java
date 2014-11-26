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


    /**
     * Update customer.
     *
     * @param customerid the customerid
     * @param customer the customer
     * @return the customer
     */
    public Customer updateCustomer(String customerid, Customer customer);
    
    /**
     * Delete customer.
     *
     * @param customerid the customerid
     */
    public void deleteCustomer(String customerid);
	
}
