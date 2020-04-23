package db; /**
 * @author: Calin Irina, I2E2
 */

//used Apache Commons


import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import javax.sql.DataSource;
import java.sql.*;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static Connection connection;
    public ResultSet rs;

    private ConnectionPool() throws SQLException {
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:oracle:thin:@localhost:1521:xe",
                "bda", "sql");
        //create a PoolableConnectionFactory that wraps the connection object created by the ConnectionFactory to add functionality
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        poolableConnectionFactory.setValidationQuery("select 1 from dual");

        //configure the pool
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(20);

        //create an instance
        GenericObjectPool<org.apache.commons.dbcp2.PoolableConnection> connectionPool = new
                GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);

        //create the data source
        DataSource dataSource = new PoolingDataSource<>(connectionPool);

        //get the specific connection
        connection = dataSource.getConnection();
    }

    public static ConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionPool();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet setResultSet(String query) { //will execute any query
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void doUpdate(String update) { //will execute updates
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(update);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public PreparedStatement createPreparedStatement(String string) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(string);
            return preparedStatement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
