/**
 * Author: Calin Irina, I2E2
 */

package Concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;

public class Player implements Runnable {
    int playerID;
    List<Token> extractedTokens;
    Board board;

    public Player(int playerID, Board board) {
        this.playerID = playerID;
        this.board = board;
        extractedTokens = Collections.synchronizedList(new ArrayList<>());
    }

    private synchronized void printProgression(int index) {
        StringBuilder stringBuilder = new StringBuilder("Progression found for player " + playerID + " with numbers: ");
        for (int i = index; i < (index + board.progressionSize); i++) {
            stringBuilder.append(extractedTokens.get(i));
            stringBuilder.append(" ");
        }
        System.out.println(stringBuilder.toString());
    }

    private boolean IsProgression(int index) {
        int lastIncrement = 0;
        boolean isFirstIncrement = true;
        for (int i = index; i < (index + board.progressionSize - 1); i++) {
            if (!extractedTokens.get(i).blank
                    && !extractedTokens.get(i + 1).blank) {
                if (isFirstIncrement) {
                    lastIncrement = extractedTokens.get(i + 1).getValue() - extractedTokens.get(i).getValue();
                    isFirstIncrement = false;
                } else if ((extractedTokens.get(i + 1).getValue() - extractedTokens.get(i).getValue()) != lastIncrement)
                    return false;
            }
        }
        return true;
    }

    public synchronized boolean HasProgression() {
        extractedTokens.sort((o1, o2) -> {
            if (o1.isBlank()) {
                return -1;
            } else if (o2.isBlank()) {
                return 1;
            } else {
                return o1.getValue() - o2.getValue();
            }
        });
        if (extractedTokens.size() < board.progressionSize)
            return false;
        for (Token token : extractedTokens) {
            int firstElementIndex = extractedTokens.indexOf(token);
            if (firstElementIndex <= extractedTokens.size() - board.progressionSize) {
                if (IsProgression(firstElementIndex)) {
                    printProgression(firstElementIndex);
                    board.gameOver=true;
                    return true;
                }
            }
        }
        return false;
    }

    public int GetLargestProgression() {
        int n = extractedTokens.size();
        int L[][] = new int[n][n];
        int llap = 2;
        for (int i = 0; i < n; i++)
            L[i][n - 1] = 2;
        // Consider every element as second element of AP
        for (int j = n - 2; j >= 1; j--) {
            // Search for i and k for j
            int i = j - 1, k = j + 1;
            while (i >= 0 && k <= n - 1) {
                if (extractedTokens.get(i).value + extractedTokens.get(k).value < 2 * extractedTokens.get(j).value)
                    k++;
                    // Before changing i, set L[i][j] as 2
                else if (extractedTokens.get(i).value + extractedTokens.get(k).value > 2 * extractedTokens.get(j).value) {
                    L[i][j] = 2;
                    i--;
                } else {
                    L[i][j] = L[j][k] + 1;
                    llap = Math.max(llap, L[i][j]);
                    i--;
                    k++;
                }
            }
            while (i >= 0) {
                L[i][j] = 2;
                i--;
            }
        }
        return llap;
    }

    public synchronized void ExtractToken() {
        if (!board.gameOver) {
            Token token = board.getToken();
            System.out.println("Player " + playerID + " extracted " + token);
            extractedTokens.add(token);
            System.out.println("Extracted tokens for player " + playerID + ": " + extractedTokens);
        }
        /* if(token.blank) {
            int LAP = GetLargestProgression();
        } */
        if (HasProgression()) {
            board.available = false;
            notifyAll();
            System.out.println("Player " + playerID + " won with the tokens: " + extractedTokens);
        }
        if (board.bound == 0 && !board.gameOver) {
            board.available = false;
            notifyAll();
            System.out.println("Tokens finished and no progression was found.");
        }
    }

    @Override
    public void run() {
        board.available = true;
        while (board.available && !board.gameOver) {
            ExtractToken();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Player " + playerID;
    }
}
