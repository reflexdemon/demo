/**
 * 
 */
package demo.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import demo.dao.CustomerDAO;
import demo.dao.utils.DebugUtils;
import demo.domain.Customer;

/**
 * @author vvenkatraman
 *
 */
public class CustomerDAOImplTest {
    CustomerDAO dao;
    private String id = null;
    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        dao = new CustomerDAOImpl();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dao = null;
    }

    @Test
    public void testAll() {
        testFindAll();
        testAddCustomer();
        testUpdateCustomer();
        testFindById();
        testDeleteCustomer();
    }

    /**
     * Test method for {@link demo.dao.impl.CustomerDAOImpl#findAll()}.
     */
//    @Test
    public void testFindAll() {
        List<Customer> result = dao.findAll();
        System.out.println(DebugUtils.jsonDebug(result, true));
        Assert.assertNotNull(result);
    }


    /**
     * Test method for {@link demo.dao.impl.CustomerDAOImpl#addCustomer(demo.domain.Customer)}.
     */
//    @Test
    public void testAddCustomer() {
        Customer newCust = new Customer();
        newCust.setName("DUMMY NAME");
        newCust.setAge("100");
        Customer created = dao.addCustomer(newCust);
        id = created.getId();
        System.out.println("Create new customer:" + created);
    }
    /**
     * Test method for {@link demo.dao.impl.CustomerDAOImpl#updateCustomer(java.lang.String, demo.domain.Customer)}.
     */
//    @Test
    public void testUpdateCustomer() {
        Assert.assertNotNull("ID Cannot be null!", id);
        Customer customer = new Customer();
        customer.setAge("10");
        Customer created = dao.updateCustomer(id, customer);
        System.out.println("Updated customer:" + created);
    }

    /**
     * Test method for {@link demo.dao.impl.CustomerDAOImpl#findById(java.lang.String)}.
     */
//    @Test
    public void testFindById() {
        Assert.assertNotNull("ID Cannot be null!", id);
        Customer result = dao.findById(id);
        System.out.println(DebugUtils.jsonDebug(result, true));
        Assert.assertNotNull(result);
    }
    /**
     * Test method for {@link demo.dao.impl.CustomerDAOImpl#deleteCustomer(java.lang.String)}.
     */
//    @Test
    public void testDeleteCustomer() {
        Assert.assertNotNull("ID Cannot be null!", id);
        dao.deleteCustomer(id);
        System.out.println("Deleted " + id);
        id = null;
    }

}
