/**
 * @Author: Calin Irina, I2E2
 */

package Concurrency;

import java.util.Scanner;

//reads from the console the index of the desired token

public class ManualPlayer extends Player {

    public ManualPlayer(int playerID, Board board) {
        super(playerID, board);
    }

    private int getUserToken() {
        Scanner scanner = new Scanner(System.in);
        boolean tokenExists = false;
        int tokenIndex = 0;
        while (board.turn != playerID) {
        } //if it's not my turn, do nothing
        if (!board.isGameOver()) {
            System.out.println("Available tokens: " + board.tokens);
            System.out.println("[Player " + playerID + "] accumulated tokens: " + this.extractedTokens);
            System.out.println("[Player " + playerID + "]: Pick a token by its index, starting from 1: ");
            while (!tokenExists && !board.isGameOver())
                try {
                    tokenIndex = Integer.parseInt(scanner.next());
                    --tokenIndex;
                    if (tokenIndex >= 0 && tokenIndex < board.bound) {
                        tokenExists = true;
                    } else {
                        System.out.println("Token with index " + (tokenIndex + 1) + " doesn't exist");
                        tokenExists = false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
        }
        return tokenIndex;
    }

    @Override
    public void run() {
        while (!board.gameOver) {
            extractToken(getUserToken());
        }
    }
}
