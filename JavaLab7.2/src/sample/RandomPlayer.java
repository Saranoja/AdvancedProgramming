/**
 * @Author: Calin Irina, I2E2
 */
package sample;
import java.util.Random;

//randomly chooses a token that's available on the board

public class RandomPlayer extends Player {
    public RandomPlayer(int playerID, Board board) {
        super(playerID, board);
    }

    public int getRandomToken() {
        int tokenIndex = 0;
        Random random = new Random();
        while (board.turn != playerID) {
        }
        if (!board.isGameOver()) {
            tokenIndex = random.nextInt(board.bound);
            System.out.println("Available tokens: " + board.tokens);
            System.out.println("[Player " + playerID + "] accumulated tokens: " + this.extractedTokens);
        }
        return tokenIndex;
    }


    @Override
    public void run() {
        while (!board.gameOver) {
            extractToken(getRandomToken());
        }
    }
}
