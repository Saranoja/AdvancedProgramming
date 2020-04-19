package controllers; /**
 * @author: Calin Irina, I2E2
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionPool;
import db.Database;
import models.*;


public class ChartController {
    Database db;
    List<Integer> artistsID = new ArrayList<>();
    ConnectionPool cp;

    public ChartController(ConnectionPool cp) {
        this.cp = cp;
    }

    public ChartController(Database db) {
        this.db = db;
    }

    public void updateChart() throws SQLException, ClassNotFoundException {
        artistsID.removeAll(artistsID);
        try {
            ResultSet resultSet = db.setResultSet("(SELECT ID FROM ARTISTS) MINUS (SELECT ARTIST_ID FROM CHART)");
            while (resultSet.next()) {
                artistsID.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        insertPopularity();
        System.out.println("Chart updated");
    }


    private void insertPopularity() {
        if (!artistsID.isEmpty()) {
            for (Integer id : artistsID) {
                try {
                    PreparedStatement pstmt = db.createPreparedStatement("INSERT INTO CHART(artist_id,popularity) " +
                            "VALUES(?,?)");
                    pstmt.setInt(1, id);
                    pstmt.setInt(2, (int) (Math.random() * 1000));
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.getStackTrace();
                }
            }
        }
    }

    public List<Artist> getChart() throws SQLException, ClassNotFoundException {
        List<Artist> artists = new ArrayList<Artist>();
        ResultSet resultSet = db.setResultSet("SELECT A.ID,A.NAME,A.COUNTRY,C.POPULARITY FROM ARTISTS A,CHART C WHERE A.ID=C.ARTIST_ID ORDER BY C.POPULARITY DESC ");
        while (resultSet.next()) {
            Artist artist = new Artist(resultSet.getString(2), resultSet.getString(3), db);
            artist.setId(resultSet.getInt(1));
            artist.setPopularity(resultSet.getInt(4));
            artists.add(artist);
        }
        return artists;
    }
}