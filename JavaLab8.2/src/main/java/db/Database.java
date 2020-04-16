package db; /**
 * @author: Calin Irina, I2E2
 */

import java.sql.*;

//Transfer object
//made the constructor private + created getInstance to make db.Database a singleton

public class Database {
    public ResultSet rs;
    private Connection con;

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() { //establishes a connection
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bda", "sql");
            System.out.println("Connected to MusicAlbums");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet setResultSet(String query) { //will execute any query
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void doUpdate(String update) { //will execute updates
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(update);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public PreparedStatement createPreparedStatement(String string) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(string);
            return preparedStatement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
