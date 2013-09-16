/**
 * 
 */
package demo.dao;

import java.sql.SQLException;

/**
 * @author ctsuser1
 * 
 */
public interface GenericDAO {

	/**
	 * Gets the connection.
	 * 
	 * @return the connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public java.sql.Connection getConnection() throws ClassNotFoundException,
			SQLException;

}
