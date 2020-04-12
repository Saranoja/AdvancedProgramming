/**
 * @Author: Calin Irina, I2E2
 */

package Concurrency;

public class ProgressionGame extends Game {
    public ProgressionGame(int players, Board board, int seconds) {
        super(players, board, seconds);
    }

    public void start() {
        System.out.println("Progression game.");
        System.out.println(seconds + " SECONDS. GO.");
        ManualPlayer p1 = new ManualPlayer(1, board);
        SmartPlayer p2 = new SmartPlayer(2, board);
        RandomPlayer p3 = new RandomPlayer(3, board);
        new Thread(p1).start();
        new Thread(p2).start();
        new Thread(p3).start();

        Thread daemon = new Thread(timekeeper);
        daemon.setDaemon(true);
        daemon.start();

        while (true) {
            if (isGameFinished()) {
                //getting the largest progression for each of the players and ending the game
                int player1Progression = p1.getLargestProgression();
                int player2Progression = p2.getLargestProgression();
                int player3Progression = p3.getLargestProgression();
                System.out.println("Player 1 largest progression: " + player1Progression);
                System.out.println("Player 2 largest progression: " + player2Progression);
                System.out.println("Player 3 largest progression: " + player3Progression);
                System.exit(0);
            }
        }
    }
}
