package demo.dao.utils;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.CommonDataSource;
import javax.sql.DataSource;

/**
 * The Class TestDataSource.
 */
public class TestDataSource implements DataSource, CommonDataSource {

    /** The out. */
    static PrintWriter out = new PrintWriter(System.out);

    /** The driver. */
    private String driver;

    /** The seconds. */
    int seconds;

    /** The password. */
    private String password;

    /** The user. */
    private String user;

    /** The url. */
    private String url;

    /**
     * Instantiates a new CBO data source.
     *
     * @param password
     *            the password
     * @param user
     *            the user
     * @param url
     *            the url
     */
    public TestDataSource(String url, String user, String password) {
        super();
        this.password = password;
        this.user = user;
        this.url = url;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.CommonDataSource#getLogWriter()
     */
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return out;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
     */
    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        this.out = out;

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.CommonDataSource#setLoginTimeout(int)
     */
    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        this.seconds = seconds;

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.CommonDataSource#getLoginTimeout()
     */
    @Override
    public int getLoginTimeout() throws SQLException {
        return seconds;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.sql.Wrapper#unwrap(java.lang.Class)
     */
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
     */
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.DataSource#getConnection()
     */
    @Override
    public Connection getConnection() throws SQLException {
        Connection c = null;
        try {
            c = getConnection(getUrl(), getUser(), getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Gets the connection.
     * 
     * @param url
     *            the url
     * @param user
     *            the user
     * @param password
     *            the password
     * @return the connection
     * @throws SQLException
     *             the sQL exception
     * @throws ClassNotFoundException
     *             the class not found exception
     */
    public Connection getConnection(String url, String user, String password)
            throws SQLException, ClassNotFoundException {
        Class.forName(getDriver());
        out.println("Getting connection to " + url + " as " + user);
        return DriverManager.getConnection(url, user, password);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.sql.DataSource#getConnection(java.lang.String,
     * java.lang.String)
     */
    @Override
    public Connection getConnection(String username, String password)
            throws SQLException {
        Connection c = null;
        try {
            c = getConnection(getUrl(), username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user
     *            the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     *
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the driver.
     *
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Sets the driver.
     *
     * @param driver
     *            the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

}
