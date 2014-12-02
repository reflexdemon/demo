/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
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
// make this class process this url.
public class DemoCustomer {
    /** The log. */
    private static Log LOG = LogFactory.getLog(DemoCustomer.class.getName());
    @Context
    private UriInfo context;

    /** The dao. */
    private final CustomerDAO dao = new CustomerDAOImpl();

    /**
     * Creates a new instance of DemoCustomer
     */
    public DemoCustomer() {
    }

    /**
     * Gets the customer.
     *
     * @param customerid
     *            the customerid
     * @return the customer
     */
    @GET
    @Path("{customerid}")
    @Produces("application/json")
    public Customer getCustomer(@PathParam("customerid") final String customerid) {
        LOG.debug("customerid:" + customerid);
        return dao.findById(customerid);

    }

    @PUT
    @Path("{customerid}")
    @Produces("application/json")
    public Customer updateCustomer(
            @PathParam("customerid") final String customerid, Customer customer) {
        LOG.debug("customerid:" + customerid);
        return dao.updateCustomer(customerid, customer);

    }

    @DELETE
    @Path("{customerid}")
    @Produces("application/json")
    public String deleteCustomer(@PathParam("customerid") final String customerid) {
        LOG.debug("deleteCustomer customerid:" + customerid);
        return dao.deleteCustomer(customerid);
        

    }

    /**
     * Gets the all customer.
     *
     * @return the all customer
     */
    @GET
    @Produces("application/json")
    public List<Customer> getAllCustomer(@Context HttpHeaders header,
            @Context HttpServletResponse response) {
        LOG.debug("Fetch All");
        return dao.findAll();

    }

    /**
     * Adds the customer.
     *
     * @param customer the customer
     * @return the customer
     */
    @POST
    @Produces("application/json")
    public Customer addCustomer(Customer customer) {
        return dao.addCustomer(customer);
    }

}
