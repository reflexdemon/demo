package demo.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import demo.dao.GenericDAO;

/**
 * Basically I am not with having this class to get
 * JDBC connection. We should have got connection from
 * Spring and Hibernate. Let me find some time on earth to do that :(
 * @author Venkateswara VP
 * 
 */
public class GenericDAOImpl implements GenericDAO {
   /** The log. */
   private static Log LOG = LogFactory.getLog(GenericDAOImpl.class.getName());
   
   /** The Constant JNDI_NAME. */
   static final String JNDI_NAME = "jdbc/MysqlDS";
   
   /* (non-Javadoc)
    * @see demo.dao.GenericDAO#getConnection()
    */
   @Override
   public Connection getConnection() throws NamingException, SQLException {
      final Context initContext = new InitialContext();
      LOG.debug("Looking up for connection");
      final Context envContext  = (Context)initContext.lookup("java:/comp/env");
      final DataSource ds = (DataSource)envContext.lookup(JNDI_NAME);
      LOG.debug("returning connection");
      return  ds.getConnection();
   }
}
