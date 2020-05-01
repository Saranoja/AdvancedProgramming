package logics;

public class Move {
    public int col;
    public int row;
    public int playerIndex;

    public Move(int row, int col, int playerIndex) {
        this.col = col;
        this.row = row;
        this.playerIndex = playerIndex;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }
}
