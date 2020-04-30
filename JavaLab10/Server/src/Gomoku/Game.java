/**
 * @author: Calin Irina, I2E2
 */

package Gomoku;

import java.util.Arrays;

//class used for checking conditions regarding the board

public class Game {
    private Player currentPlayer;
    private Board board;

    public Game(Board board) {
        this.board = board;
    }

    public boolean isBoardFull() {
        return Arrays.stream(board.getBoard()).allMatch(p -> p != null);
    }

    public boolean hasWinner() {
        //vertical winning condition
        for (int i = 0; i < board.getSize(); ++i) {
            int count = 0;
            for (int j = 1; j < board.getSize(); ++j) {
                if (board.getBoard()[i][j] == board.getBoard()[i][j - 1] &&
                        (board.getBoard()[i][j] == 1 || board.getBoard()[i][j] == 2))
                    ++count;
                else
                    count = 0;
                if (count == 4) //if we get 5 consecutive tiles with the same mark
                    return true;
            }
            count = 0;
        }
        //horizontal winning condition
        for (int j = 0; j < board.getSize(); ++j) {
            int count = 0;
            for (int i = 1; i < board.getSize(); ++i) {
                if (board.getBoard()[i][j] == board.getBoard()[i - 1][j] &&
                        (board.getBoard()[i][j] == 1 || board.getBoard()[i][j] == 2))
                    ++count;
                else
                    count = 0;
                if (count == 4) //if we get 5 consecutive tiles with the same mark
                    return true;
            }
            count = 0;
        }
        return false;
    }

    public synchronized void move(int row, int col, Player player) {
        if (!player.equals(currentPlayer)) {
            throw new IllegalStateException("Not your turn");
        } else if (player.opponent == null) {
            throw new IllegalStateException("You don't have an opponent yet");
        } else if (board.getBoard()[col][row] != 0) {
            throw new IllegalStateException("Cell already occupied");
        }
        board.getBoard()[col][row] = currentPlayer.getMark();
        currentPlayer = currentPlayer.opponent;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
