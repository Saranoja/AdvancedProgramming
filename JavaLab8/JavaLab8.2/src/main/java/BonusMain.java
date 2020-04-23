import ThreadPoolExecutor.TPE;/**
 * @author: Calin Irina, I2E2
 */

import java.sql.SQLException;

import db.*;

public class BonusMain {
    //create a (large?) number of concurrent tasks, each requiring a database connection in order to perform various SQL operations on the database
    //create a scenario in order to highlight the advantages of using a connection pool
    //applications should use a connection pool whenever database usage is known to affect application performance
    //in this case: reduce the amount of effort required to manually manage connection objects (each connection has the same purpose)
    //create 500 threads to be run concurrently
    public static void main(String[] args) throws SQLException {
        ConnectionPool cp = ConnectionPool.getInstance();
        TPE tpe = new TPE(500, cp);
        tpe.start();
        cp.getConnection().close();
    }
}
