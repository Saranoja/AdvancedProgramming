/**
 * @Author: Calin Irina, I2E2
 */

package Concurrency;

import java.util.ArrayList;

//changed the approach and implemented a semaphor to notify on turns
//round will be incremented after each player extracts a token
//turn will be round mod nrPlayers

public class Board {
    ArrayList<Token> tokens;
    public int bound = 30;
    int progressionSize;
    public volatile boolean gameOver = false;
    public int round = 0;
    public int nrPlayers;
    public volatile int turn = 1;

    //added players number in constructor to keep track of the turn
    public Board(ArrayList<Token> tokens, int size, int playersNumber) {
        this.tokens = tokens;
        this.progressionSize = size;
        this.nrPlayers = playersNumber;
    }

    //updated function parameters so the player can extract a token depending on his strategy
    public synchronized Token getToken(Player player, int tokenIndex) {
        Token chosen = new Token(0);
        while (turn != player.playerID) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!gameOver && bound > 0) {
            chosen = tokens.get(tokenIndex);
            player.extractedTokens.add(chosen);
            tokens.remove(tokenIndex);
            bound--;
            System.out.println("Player " + player.playerID + " extracted token with value " + chosen.value);
            System.out.println("");
            if (player.hasProgression()) {
                System.out.println("Player " + player.playerID + " has won.");
                System.out.println("Winning combination: " + player.extractedTokens);
                gameOver = true;
                notifyAll();
            }
        }
        if (bound == 0)
            gameOver = true;
        ++round;
        turn = round % nrPlayers + 1;
        notifyAll();
        return chosen;
    }

    public boolean isGameOver() {
        return gameOver;
    }

}
