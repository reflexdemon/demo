/**
 * 
 */
package demo.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import demo.dao.GenericDAO;

/**
 * @author ctsuser1
 * 
 */
public class GenericDAOImpl implements GenericDAO {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// static final String DB_URL =
	// "mysql://adminMNUa44i:VLhaLhH_vBXK@127.5.109.2:3306/";
	static final String DB_URL = "jdbc:mysql://localhost:3306/demo";

	// Database credentials
	static final String USER = "adminMNUa44i";
	static final String PASS = "VLhaLhH_vBXK";

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		return DriverManager.getConnection(DB_URL, USER, PASS);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
