/**
 * @Author: Calin Irina, I2E2
 */

package Concurrency;

public abstract class Game {
    int nrPlayers;
    int seconds;
    Board board;
    Timekeeper timekeeper;

    public Game() {}

    public Game(int players, Board board, int seconds) {
        this.nrPlayers = players;
        this.board = board;
        this.seconds = seconds;
        timekeeper = new Timekeeper(seconds);
    }

    public boolean isGameFinished() {
        if (timekeeper.isGameOver()) {
            board.gameOver = true;
            System.out.println("Draw. Time expired. " + seconds + " seconds. Y'all lost, boomers. However... the best of the worst: ");
            timekeeper.stopGame();
            return true;
        } else if (board.isGameOver()) {
            timekeeper.stopGame();
            return true;
        }
        return false;
    }

    public void start() {
        System.out.println(seconds + " SECONDS. GO.");
    }
}
