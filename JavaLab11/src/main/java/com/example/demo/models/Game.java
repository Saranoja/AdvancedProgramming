package com.example.demo.models;

/**
 * @author : Calin Irina, I2E2
 */

import java.util.Objects;

public class Game {
    public String player1;
    public String player2;
    public int id;

    public Game(String player1, String player2, int id) {
        this.player1 = player1;
        this.player2 = player2;
        this.id = id;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Game{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id &&
                Objects.equals(player1, game.player1) &&
                Objects.equals(player2, game.player2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1, player2, id);
    }
}