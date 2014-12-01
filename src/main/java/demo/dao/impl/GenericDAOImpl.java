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
import demo.dao.utils.TestDataSource;

/**
 * Basically I am not with having this class to get JDBC connection. We should
 * have got connection from Spring and Hibernate. Let me find some time on earth
 * to do that :(
 * 
 * @author Venkateswara VP
 * 
 */
public class GenericDAOImpl implements GenericDAO {
    /** The log. */
    private static Log LOG = LogFactory.getLog(GenericDAOImpl.class.getName());

    /** The Constant JNDI_NAME. */
    static final String JNDI_NAME = "jdbc/MysqlDS";

    static final String MODE = System.getProperty("mode");

    /** The url. */
    private String url = "jdbc:mysql://localhost:3306/demo";

    /** The user. */
    private String user = "adminMNUa44i";

    /** The pass. */
    private String pass = "VLhaLhH_vBXK";

    private String driver = "com.mysql.jdbc.Driver";

    /*
     * (non-Javadoc)
     * 
     * @see demo.dao.GenericDAO#getConnection()
     */
    @Override
    public Connection getConnection() throws NamingException, SQLException {

        DataSource ds = null;
        if (!isDev()) {
            ds = getDataSource();
        } else {
            ds = getDevSource();
        }
        LOG.debug("returning connection");
        return ds.getConnection();
    }

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
    
    private DataSource getDataSource() throws NamingException {
        final Context initContext = new InitialContext();
        LOG.debug("Looking up for connection");
        final Context envContext = (Context) initContext
                .lookup("java:/comp/env");
        final DataSource ds = (DataSource) envContext.lookup(JNDI_NAME);
        return ds;
    }

    protected boolean isDev() {
        LOG.debug("Debug Mode:::" + MODE);
        return (MODE == null) ? false : MODE.equalsIgnoreCase("dev");
    }
}
