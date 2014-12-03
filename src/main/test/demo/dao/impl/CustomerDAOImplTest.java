/**
 * 
 */
package demo.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import demo.dao.CustomerDAO;
import demo.dao.GenericDAO;
import demo.dao.utils.DebugUtils;
import demo.dao.utils.DemoException;
import demo.dao.utils.TestDataSource;
import demo.domain.Customer;

/**
 * @author vvenkatraman
 *
 */
public class CustomerDAOImplTest {
    static final String MODE = System.getProperty("mode");

    /** The url. */
    private String url = "jdbc:mysql://localhost:3306/demo";

    /** The user. */
    private String user = "adminMNUa44i";

    /** The pass. */
    private String pass = "VLhaLhH_vBXK";

    private String driver = "com.mysql.jdbc.Driver";
    /**
     * Gets the dev source.
     *
     * @return the dev source
     */
    private DataSource getDevSource() {
        TestDataSource ds = new TestDataSource(url, user, pass); 
                ds.setDriver(driver);
        return ds;
    }    
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
        GenericDAOImpl.setSource(getDevSource());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dao = null;
    }

    @Test
    public void testAll() throws DemoException {
        testFindAll();
        testAddCustomer();
        testUpdateCustomer();
        testFindById();
        testDeleteCustomer();
    }

    /**
     * Test method for {@link demo.dao.impl.CustomerDAOImpl#findAll()}.
     * @throws DemoException 
     */
//    @Test
    public void testFindAll() throws DemoException {
        List<Customer> result = dao.findAll();
        System.out.println(DebugUtils.jsonDebug(result, true));
        Assert.assertNotNull(result);
    }


    /**
     * Test method for {@link demo.dao.impl.CustomerDAOImpl#addCustomer(demo.domain.Customer)}.
     * @throws DemoException 
     */
//    @Test
    public void testAddCustomer() throws DemoException {
        Customer newCust = new Customer();
        newCust.setName("DUMMY NAME");
        newCust.setAge("123");
        Customer created = dao.addCustomer(newCust);
        id = created.getId();
        System.out.println("Create new customer:" + created);
    }
    /**
     * Test method for {@link demo.dao.impl.CustomerDAOImpl#updateCustomer(java.lang.String, demo.domain.Customer)}.
     * @throws DemoException 
     */
//    @Test
    public void testUpdateCustomer() throws DemoException {
        Assert.assertNotNull("ID Cannot be null!", id);
        Customer customer = new Customer();
        customer.setAge("10");
        Customer created = dao.updateCustomer(id, customer);
        System.out.println("Updated customer:" + created);
    }

    /**
     * Test method for {@link demo.dao.impl.CustomerDAOImpl#findById(java.lang.String)}.
     * @throws DemoException 
     */
//    @Test
    public void testFindById() throws DemoException {
        Assert.assertNotNull("ID Cannot be null!", id);
        Customer result = dao.findById(id);
        System.out.println(DebugUtils.jsonDebug(result, true));
        Assert.assertNotNull(result);
    }
    /**
     * Test method for {@link demo.dao.impl.CustomerDAOImpl#deleteCustomer(java.lang.String)}.
     * @throws DemoException 
     */
//    @Test
    public void testDeleteCustomer() throws DemoException {
        Assert.assertNotNull("ID Cannot be null!", id);
        dao.deleteCustomer(id);
        System.out.println("Deleted " + id);
        id = null;
    }

}
