/**
 * 
 */
package demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import demo.dao.CustomerDAO;
import demo.domain.Customer;

/**
 * The Class CustomerDAOImpl.
 * 
 * @author Venkateswara VP
 */
public class CustomerDAOImpl extends GenericDAOImpl implements CustomerDAO {
   
   /** The log. */
   private static final Log    LOG          = LogFactory
         .getLog(CustomerDAOImpl.class
               .getName());
   
   /** The Constant GET_CUSTOMER. */
   private static final String GET_CUSTOMER = "SELECT ID, NAME, AGE FROM CUSTOMER WHERE ID = ? ";
   
   /** The Constant GET_ALL. */
   private static final String GET_ALL      = "SELECT ID, NAME, AGE FROM CUSTOMER";
   
   /* (non-Javadoc)
    * @see demo.dao.CustomerDAO#findById(java.lang.String)
    */
   @Override
   public Customer findById(final String id) {
      LOG.debug("entering findById with " + id);
      final Customer customer = new Customer();
      int parameterIndex = 0;
      Connection connection = null;
      PreparedStatement statement = null;
      try {
         connection = getConnection();
         statement = connection.prepareStatement(GET_CUSTOMER);
         statement.setInt(++parameterIndex, Integer.parseInt(id));
         LOG.debug("Executing statement:" + GET_CUSTOMER);
         final ResultSet resultset = statement.executeQuery();
         if (resultset.next()) {
            customer.setId(resultset.getString("ID"));
            customer.setName(resultset.getString("NAME"));
            customer.setAge(resultset.getString("AGE"));
         }
      }
      catch (final SQLException e) {
         LOG.error(e.getMessage(), e);
      }
      catch (final NamingException e) {
         LOG.error(e.getMessage(), e);
      }
      finally {
         if (null != connection) {
            try {
               connection.close();
            }
            catch (final SQLException e) {
               throw new RuntimeException("Unable to close connection!", e);
            }
         }
      }
      LOG.debug("exitting findById with " + customer);
      return customer;
   }
   
   /* (non-Javadoc)
    * @see demo.dao.CustomerDAO#findAll()
    */
   @Override
   public List<Customer> findAll() {
      LOG.debug("entering findAll");
      final List<Customer> list = new ArrayList<Customer>();
      Connection connection = null;
      PreparedStatement statement = null;
      try {
         connection = getConnection();
         statement = connection.prepareStatement(GET_ALL);
         LOG.debug("Executing statement:" + GET_ALL);
         final ResultSet resultset = statement.executeQuery();
         while (resultset.next()) {
            final Customer customer = new Customer();
            customer.setId(resultset.getString("ID"));
            customer.setName(resultset.getString("NAME"));
            customer.setAge(resultset.getString("AGE"));
            list.add(customer);
         }
      }
      catch (final SQLException e) {
         LOG.error(e.getMessage(), e);
      }
      catch (final NamingException e) {
         LOG.error(e.getMessage(), e);
      }
      finally {
         if (null != connection) {
            try {
               connection.close();
            }
            catch (final SQLException e) {
               throw new RuntimeException("Unable to close connection!", e);
            }
         }
      }
      LOG.debug("exitting findAll with " + list);
      return list;
   }
   
}
