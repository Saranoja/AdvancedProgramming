package controllers; /**
 * @author: Calin Irina, I2E2
 */

import java.sql.ResultSet;

import db.ConnectionPool;
import db.Database;

//DAO

public class ArtistController {
    Database db;
    ConnectionPool cp;

    public ArtistController(ConnectionPool cp) {
        this.cp = cp;
    }

    public ArtistController(Database db) {
        this.db = db;
    }

    public void create(String name, String country) {
        String query = "INSERT INTO artists(name, country) values('" + name + "','" + country + "')";
        db.doUpdate(query);
        System.out.println(name + " added to artists.");
    }

    public void CPcreate(String name, String country) {
        String query = "INSERT INTO artists(name, country) values('" + name + "','" + country + "')";
        cp.doUpdate(query);
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

    public int getId(String name) {
        ResultSet rs = db.setResultSet("SELECT id from artists where name = '" + name + "where rownum<2'");
        try {
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
