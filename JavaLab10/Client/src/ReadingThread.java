import java.io.IOException;
import java.util.Scanner;

public class ReadingThread implements Runnable {
    private Scanner in;
    private String response;
    private Board board;
    private int myIndex;
    private volatile String command = "init";

    public ReadingThread(Scanner scanner, Board board, int myIndex) {
        this.in = scanner;
        this.board = board;
        this.myIndex = myIndex;
    }

    public synchronized void setCommand(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        while (in.hasNextLine()) {
            response = in.nextLine();
            System.out.println("Response: " + response);

            if (!response.startsWith("Exception") && !response.startsWith("Unknown")) {
                String[] pieces = command.split(" ");
                int col = Integer.parseInt(pieces[1]);
                int row = Integer.parseInt(pieces[2]);
                board.setCell(row, col, myIndex);
            }

            if (response.startsWith("Opponent put piece on")) {
                String[] pieces = response.split(" ");
                int col = Integer.parseInt(pieces[4]);
                int row = Integer.parseInt(pieces[5]);
                board.setCell(row, col, 3 - myIndex);

                for (int i = 0; i < 19; ++i) {
                    for (int j = 0; j < 19; ++j)
                        System.out.print(board.getBoard()[i][j] + " ");
                    System.out.println();
                }
            }


            if (response.endsWith("VICTORY!") || response.endsWith("DEFEAT!"))
                break;

            if (response.equals("Sorry, the other player left the game."))
                break;
        }
        System.exit(0);
    }
}

