/**
 * @author: Calin Irina, I2E2
 */

package JDBC;

import java.sql.ResultSet;

//DAO

public class ArtistController {
    Database db;

    public ArtistController(Database db) {
        this.db = db;
    }

    public void create(String name, String country) {
        String query = "INSERT INTO artists(name, country) values('" + name + "','" + country + "')";
        db.doUpdate(query);
        System.out.println(name + " added to artists.");
    }

    public void findByName(String name) {
        ResultSet rs = db.setResultSet("SELECT id from artists where name = '" + name + "'");
        try {
            while (rs.next())
                System.out.println("ID for " + name + ": " + rs.getInt(1));
        } catch (Exception e) {
            System.out.println("Name not found");
        }
    }
}
