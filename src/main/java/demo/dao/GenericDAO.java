/**
 * 
 */
package demo.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

/**
 * @author Venkateswara VP
 * 
 */
public interface GenericDAO {
   
   /**
    * Gets the connection.
    * 
    * @return the connection
    * @throws SQLException
    * @throws NamingException
    */
   public java.sql.Connection getConnection() throws NamingException, SQLException;
   
}
