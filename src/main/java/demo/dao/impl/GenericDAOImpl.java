/**
 * 
 */
package demo.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import demo.dao.GenericDAO;

/**
 * @author ctsuser1
 * 
 */
public class GenericDAOImpl implements GenericDAO {

	/** The Constant JNDI_NAME. */
	static final String JNDI_NAME = "jdbc/MysqlDS"; 
	
	@Override
	public Connection getConnection() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup(JNDI_NAME);
		return  ds.getConnection();
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
