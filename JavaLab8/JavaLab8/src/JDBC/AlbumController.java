/**
 * @author: Calin Irina, I2E2
 */

package JDBC;

import java.sql.*;

//DAO

public class AlbumController {
    Database db;

    public AlbumController(Database db) {
        this.db = db;
    }

    public void create(String name, int artistId, int releaseYear) {
        String query = "INSERT INTO albums(name, artist_id, release_year) values('" + name + "'," + artistId + "," + releaseYear + ")";
        db.doUpdate(query);
        System.out.println(name + " added to albums.");
    }

    public void findByArtist(int artistId) {
        ResultSet rs = db.setResultSet("SELECT name from albums where artist_id = " + artistId);
        try {
            while (rs.next())
                System.out.println("Album for the artist with id " + artistId + ": " + rs.getString(1));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
