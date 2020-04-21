package models;/**
 * @author: Calin Irina, I2E2
 */

import db.Database;


public class Album {
    public int id = 10;
    private String name;
    private int artistID;
    private int releaseYear;
    private Database db;

    public Album(String name, int year, int artistID, Database db) {
        this.db = db;
        this.name = name;
        this.releaseYear = year;
        this.artistID = artistID;
    }

    public int getreleaseYear() {
        return releaseYear;
    }

    public void setreleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getartistID() {
        return artistID;
    }

    public void setartistID(int artistID) {
        this.artistID = artistID;
    }

}

