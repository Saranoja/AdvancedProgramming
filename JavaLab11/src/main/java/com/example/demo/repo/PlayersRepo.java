package com.example.demo.repo;

/**
 * @author : Calin Irina, I2E2
 */

import com.example.demo.Database;
import com.example.demo.models.Player;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlayersRepo {
    private static Database db = Database.getInstance();
    public static List<Player> players = new ArrayList<Player>();

    public static void addPlayer(String id, String name) {
        String query = "INSERT INTO player(id, name) values('" + id + "','" + name + "')";
        db.doUpdate(query);
        players.add(new Player(id, name));
        System.out.println(name + " has been added to the game.");
    }

    public static void addPlayer(Player player) {
        String query = "INSERT INTO player(id, name) values('" + player.getId() + "','" + player.getName() + "')";
        db.doUpdate(query);
        players.add(new Player(player.getId(), player.getName()));
        System.out.println(player.getName() + " has been added to the database.");
    }


    public static void updatePlayer(String id, String newName) {
        String query = "UPDATE player set name= '" + newName + "' where id='" + id + "'";
        db.doUpdate(query);
        System.out.println(id + " got updated to " + newName);
    }


    public static void deletePlayer(String id) {
        String query = "DELETE FROM player where id='" + id + "'";
        db.doUpdate(query);
        System.out.println("The player with the id " + id + " has been deleted successfully");
    }

    public static List<Player> showList() {
        Player player = null;
        ResultSet rs = db.setResultSet("SELECT id, name from player");
        try {
            while (rs.next()) {
                player = new Player(rs.getString(1), rs.getString(2));
                players.add(player);
            }
        } catch (Exception e) {
            System.out.println("Empty table");
        }
        return players;
    }

    public static Player showPlayer(String id) {
        Player player = null;
        ResultSet rs = db.setResultSet("SELECT id, name from player where id= '" + id + "'");
        try {
            while (rs.next()) {
                player = new Player(rs.getString(1), rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("Player doesn't exist");
        }
        return player;
    }
}
