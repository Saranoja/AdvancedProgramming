/**
 * @Author: Calin Irina, I2E2
 */
package sample;
import java.util.Arrays;
import java.util.Random;

//extracts tokens in order to expand his progression - if possible
//first two tokens are extracted randomly
//if the desired token in order to extend the progression doesn't exist, he will extract a random one

public class SmartPlayer extends Player {
    public SmartPlayer(int playerID, Board board) {
        super(playerID, board);
    }

    int findMissingUtil(int arr[], int low, int high, int diff) {
        if (high <= low)
            return Integer.MAX_VALUE;
        int mid = low + (high - low) / 2;
        if (extractedTokens.get(mid + 1).getValue() - extractedTokens.get(mid).getValue() != diff)
            return (extractedTokens.get(mid).getValue() + diff);
        if (mid > 0 && arr[mid] - arr[mid - 1] != diff)
            return (arr[mid - 1] + diff);
        if (arr[mid] == arr[0] + mid * diff)
            return findMissingUtil(arr, mid + 1, high, diff);
        return findMissingUtil(arr, low, mid - 1, diff);
    }

    public int getSmartToken() {
        int temp = -1;
        while (board.turn != playerID) { //if it's not my turn, do nothing
        }
        if (!board.isGameOver()) {
            if (extractedTokens.size() >= 2) {
                int low = 99;
                int high = 0;
                for (Token i : extractedTokens)
                    if (low > i.getValue())
                        low = i.getValue();
                for (Token i : extractedTokens)
                    if (high < i.getValue())
                        high = i.getValue();
                int cnt = 1;
                int[] arr = new int[extractedTokens.size() + 1];
                for (Token i : extractedTokens) {
                    arr[cnt] = (int) i.getValue();
                    cnt++;
                }
                int diff = (high - low) / (arr.length - 1);
                Arrays.sort(arr);
                temp = findMissingUtil(arr, 0, extractedTokens.size() - 1, diff);
                int index = -1;
                boolean ok = false;
                for (Token j : board.tokens) {
                    if (temp == j.getValue()) {
                        index = board.tokens.indexOf(j);
                        --index;
                        ok = true;
                    }
                }
                if (ok == true && index>0)
                    return index;
            }
            Random rand = new Random();
            temp = rand.nextInt(board.bound - 1) + 1;
            System.out.println("Available tokens: " + board.tokens);
            System.out.println("[Player " + playerID + "] accumulated tokens: " + this.extractedTokens);
        }
        return temp;
    }


    @Override
    public void run() {
        while (!board.gameOver) {
            extractToken(getSmartToken());
        }
    }
}
