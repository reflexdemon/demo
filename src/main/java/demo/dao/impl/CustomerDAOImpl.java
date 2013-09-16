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
 * @author ctsuser1
 * 
 */
public class CustomerDAOImpl extends GenericDAOImpl implements CustomerDAO {
	private static Log LOG = LogFactory.getLog(CustomerDAOImpl.class.getName());
	private static final String GET_CUSTOMER = "SELECT ID, NAME, AGE FROM CUSTOMER WHERE ID = ? ";
	private static final String GET_ALL = "SELECT ID, NAME, AGE FROM CUSTOMER";

	@Override
	public Customer findById(String id) {
		LOG.debug("entering findById with " + id);
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
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
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
		LOG.debug("exitting findById with " + customer);
		return customer;
	}

	@Override
	public List<Customer> findAll() {
		LOG.debug("entering findAll");
		List<Customer> list = new ArrayList<Customer>();
		int parameterIndex = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(GET_ALL);
			LOG.debug("Executing statement:" + GET_ALL);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				Customer customer = new Customer();
				customer.setId(resultset.getString("ID"));
				customer.setName(resultset.getString("NAME"));
				customer.setAge(resultset.getString("AGE"));
				list.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
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
		LOG.debug("exitting findAll with " + list);
		return list;
	}

}
