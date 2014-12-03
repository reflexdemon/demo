package demo.dao;

import java.util.List;

import demo.dao.utils.DemoException;
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
	 * @throws DemoException 
	 */
	public Customer findById(String id) throws DemoException;

	/**
	 * Find all.
	 *
	 * @return the customer
	 * @throws DemoException 
	 */
	public List<Customer> findAll() throws DemoException;


    /**
     * Update customer.
     *
     * @param customerid the customerid
     * @param customer the customer
     * @return the customer
     * @throws DemoException 
     */
    public Customer updateCustomer(String customerid, Customer customer) throws DemoException;
    
    /**
     * Delete customer.
     *
     * @param customerid the customerid
     * @return 
     * @throws DemoException 
     */
    public String deleteCustomer(String customerid) throws DemoException;

    /**
     * Adds the customer.
     *
     * @param customer the customer
     * @return the customer
     * @throws DemoException 
     */
    public Customer addCustomer(Customer customer) throws DemoException;
	
}
