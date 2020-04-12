/**
 * @Author: Calin Irina, I2E2
 */

package Concurrency;

import java.util.stream.IntStream;

//this game will be played at a different board, with specific tokens
//players alternately pick edges, trying to complete a clique of a given size
public class CliqueGame extends Game {
    private Graph graph;
    //private int cliqueSize;

    public CliqueGame(int players, int n, int seconds, Graph g) {
        this.nrPlayers = players;
        this.seconds = seconds;
        graph = g;
        timekeeper = new Timekeeper(seconds);
    }

    public void start() {
        System.out.println("Clique game.");
        System.out.println(seconds + " SECONDS. GO.");
        var players = IntStream.rangeClosed(1, nrPlayers).mapToObj(i -> new CliquePlayer(i,graph)).toArray(CliquePlayer[]::new);
        for(CliquePlayer player : players) {
            new Thread(player).start();
        }

        Thread daemon = new Thread(timekeeper);
        daemon.setDaemon(true);
        daemon.start();

        while (true) {
            if (isGameFinished()) {
                //getting the largest progression for each of the players and ending the game
                for(CliquePlayer player : players)
                System.out.println("Player " + player.playerID + " max clique size: " + player.getScore());
                System.exit(0);
            }
        }

    }

    @Override
    public boolean isGameFinished() {
        if (timekeeper.isGameOver()) {
            graph.gameOver = true;
            System.out.println("Draw. Time expired. " + seconds + " seconds. Y'all lost, boomers. However... the best of the worst: ");
            timekeeper.stopGame();
            return true;
        } else if (graph.gameOver) {
            timekeeper.stopGame();
            return true;
        }
        return false;
    }
}
