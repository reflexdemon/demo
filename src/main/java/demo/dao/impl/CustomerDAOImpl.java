/**
 * 
 */
package demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import demo.dao.CustomerDAO;
import demo.domain.Customer;

/**
 * @author ctsuser1
 * 
 */
public class CustomerDAOImpl extends GenericDAOImpl implements CustomerDAO {
	private static Log LOG = LogFactory.getLog(CustomerDAOImpl.class.getName());
	private static final String GET_CUSTOMER = "SELECT ID, NAME, AGE FROM CUSTOMER WHERE ID = ? ";

	@Override
	public Customer getCustomer(String id) {
		LOG.debug("entering getCustomer with " + id);
		Customer customer = new Customer();
		int parameterIndex = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(GET_CUSTOMER);
			statement.setInt(++parameterIndex, Integer.parseInt(id));
			LOG.debug("Executing statement:" + GET_CUSTOMER);
			ResultSet resultset = statement.executeQuery();
			if (resultset.next()) {
				customer.setId(resultset.getString("ID"));
				customer.setName(resultset.getString("NAME"));
				customer.setAge(resultset.getString("AGE"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new RuntimeException("Unable to close connection!");
				}
			}
		}
		LOG.debug("exitting getCustomer with " + customer);
		return customer;
	}

}
