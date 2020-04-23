package controllers; /**
 * @author: Calin Irina, I2E2
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.ConnectionPool;
import db.Database;

//DAO

public class AlbumController {
    Database db;
    ConnectionPool cp;

    public AlbumController(ConnectionPool cp) {
        this.cp = cp;
    }

    public AlbumController(Database db) {
        this.db = db;
    }

    public void create(String name, int artistId, int releaseYear) {
        String query = "INSERT INTO albums(name, artist_id, release_year) values('" + name + "'," + artistId + "," + releaseYear + ")";
        db.doUpdate(query);
        System.out.println(name + " added to albums.");
    }

    public void CPcreate(String name, int artistId, int releaseYear) {
        String query = "INSERT INTO albums(name, artist_id, release_year) values('" + name + "'," + artistId + "," + releaseYear + ")";
        cp.doUpdate(query);
        System.out.println(name + " added to albums.");
    }

    public void findByArtist(int artistId) {
        ResultSet rs = db.setResultSet("SELECT name from albums where artist_id = " + artistId);
        try {
            while (rs.next())
                System.out.println("models.Album for the artist with id " + artistId + ": " + rs.getString(1));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
