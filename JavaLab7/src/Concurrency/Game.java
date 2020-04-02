/**
 * Author: Calin Irina, I2E2
 */

package Concurrency;

import java.util.stream.IntStream;

public class Game {
    int nrPlayers;
    Board board;

    public Game(int players, Board board) {
        this.nrPlayers = players;
        this.board = board;
    }

    public void Start() {
        var players = IntStream.rangeClosed(1,nrPlayers).mapToObj(i-> new Player(i,board)).toArray(Player[]::new);
        for (Player p : players)
        {
            new Thread(p).start();
        }
    }
}
