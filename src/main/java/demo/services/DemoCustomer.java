/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import demo.dao.CustomerDAO;
import demo.dao.impl.CustomerDAOImpl;
import demo.domain.Customer;

/**
 * REST Web Service
 * 
 * @author ctsuser1
 */
@Path("customer")
// make this class process this url. empno is a variable that represents
// employee number.
public class DemoCustomer {
	/** The log. */
	private static Log LOG = LogFactory.getLog(DemoCustomer.class.getName());
	@Context
	private UriInfo context;

	/** The dao. */
	private CustomerDAO dao = new CustomerDAOImpl();

	/**
	 * Creates a new instance of DemoCustomer
	 */
	public DemoCustomer() {
	}

	@GET
	@Path("/data/{customerid}")
	@Produces("application/json")
	public Customer getJson(@PathParam("customerid") String customerid) {
		LOG.debug("customerid:" + customerid);
		return dao.getCustomer(customerid);

	} // end of
}
