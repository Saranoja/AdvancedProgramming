package com.example.demo.repo;

/**
 * @author : Calin Irina, I2E2
 */

import com.example.demo.Database;
import com.example.demo.models.Game;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GamesRepo {
    private static Database db = Database.getInstance();
    public static List<Game> games = new ArrayList<Game>();

    public static void addGame(int id, String id1, String id2) {
        String query = "INSERT INTO game(id, player1_id, player2_id) values(" + id + ",'" + id1 + "','" + id2 + "')";
        db.doUpdate(query);
        games.add(new Game(id1, id2, id));
        System.out.println("Game " + id + " has been added to the database.");
    }

    public static void addGame(Game game) {
        String query = "INSERT INTO game(id, player1_id, player2_id) values(" + game.getId() + ",'" + game.getPlayer1()
                + "','" + game.getPlayer2() + "')";
        db.doUpdate(query);
        games.add(new Game(game.getPlayer1(), game.getPlayer2(), game.getId()));
        System.out.println("Game " + game.getId() + " has been added to the database.");
    }


    public static void deleteGame(String id) {
        String query = "DELETE FROM game where id=" + id;
        db.doUpdate(query);
        System.out.println("The game with the id " + id + " has been deleted successfully");
    }

    public static List<Game> showList() {
        Game game = null;
        ResultSet rs = db.setResultSet("SELECT id, player1_id, player2_id from game");
        try {
            while (rs.next()) {
                game = new Game(rs.getString(2), rs.getString(3), rs.getInt(1));
                games.add(game);
            }
        } catch (Exception e) {
            System.out.println("Empty table");
        }
        return games;
    }

    public static Game showGame(String id) {
        Game game = null;
        ResultSet rs = db.setResultSet("SELECT id, player1_id, player2_id from game where id= " + id);
        try {
            while (rs.next()) {
                game = new Game(rs.getString(2), rs.getString(3), rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("Game doesn't exist");
        }
        return game;
    }
}
