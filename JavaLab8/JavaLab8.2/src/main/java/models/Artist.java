package models; /**
 * @author: Calin Irina, I2E2
 */

import db.Database;

import java.sql.*;

public class Artist {

    public String name;
    private int id;
    private String country;
    public int popularity;
    private Database db;

    public Artist(String name, String country, Database db) throws SQLException, ClassNotFoundException {
        this.name = name;
        this.country = country;
        this.db = db;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }


    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
