/**
 * Author: Calin Irina, I2E2
 */

package Concurrency;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Board {
    ArrayList<Token> tokens;
    public boolean available = false;
    public int bound = 20;
    int progressionSize;
    public boolean gameOver = false;

    public Board(ArrayList<Token> tokens, int size) {
        this.tokens = tokens;
        this.progressionSize = size;
    }

    public synchronized Token getToken() {
        while(!gameOver) {
            while (!available) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            available = false;
            notifyAll();
            Random r = new Random();
            int randomToken = r.nextInt(bound);
            Token chosen = tokens.get(randomToken);
            tokens.remove(randomToken);
            bound--;
            available = true;
            notifyAll();
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return chosen;
        }
        Token t = new Token(0);
        return t;
    }
}
