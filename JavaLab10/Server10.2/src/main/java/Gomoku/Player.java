/**
 * @author: Calin Irina, I2E2
 */

package Gomoku;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import logics.Sftp;

//setup() creates an intro for any player that joins the server
//when 2 players have joined, the server starts the communication between them using *opponent* as a semaphore

public class Player implements Runnable {
    public int mark;
    public volatile Player opponent;
    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    private Game game;

    public Player(int mark, Game game) {
        this.mark = mark;
        this.game = game;
    }

    private void setup() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        output.println("WELCOME " + mark);
        System.out.println("I said welcome");
        if (mark == 1) {
            game.setCurrentPlayer(this);
            output.println("Waiting for opponent to connect");
            System.out.println("Waiting for a second player to join...");
        } else {
            opponent = game.getCurrentPlayer();
            opponent.opponent = this;
            output.println("You are the second player. Wait for your opponent to move first.");
            opponent.output.println("Your move");
            System.out.println("Game on.");
        }
    }

    private void processCommands() {
        System.out.println("Processing commands...");
        while (input.hasNextLine()) {
            String command = input.nextLine();
            System.out.println("Got command from player " + this.mark + ": " + command);
            if (command.toLowerCase().startsWith("move")) {
                String[] parts = command.split(" ");
                int row = Integer.parseInt(parts[1]);
                int col = Integer.parseInt(parts[2]);
                processMoveCommand(col, row);
            } else if (command.toLowerCase().equals("stop")) {
                System.out.println("Player " + mark + " left");
                try {
                    //submit the report
                    game.report.putMoves(game.allMoves);
                    game.report.flushAll();
                    System.out.println("Game report created successfully");

                    socket.close(); //close the socket

                    String localPath = "C:/Users/Irina/Desktop/AdvancedProgramming/JavaLab10/Server10.2/";
                    String remotePath = ".";

                    Sftp ftp = new Sftp("127.0.0.1", 80, "root");

                    ftp.upload(localPath + "Gomoku.html", remotePath);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.println("Game over");
                break;
            } else {
                System.out.println("Unknown command");
                output.println("Unknown command");
            }
        }
    }

    private void processMoveCommand(int column, int row) {
        try {
            game.move(column, row, this);
            for (int i = 0; i < 19; ++i) {
                for (int j = 0; j < 19; ++j)
                    System.out.print(game.getBoard().getBoard()[i][j] + " ");
                System.out.println();
            }
            if (game.hasWinner()) {
                output.println("Moved " + row + " " + column + " - VICTORY!");
                opponent.output.println("Opponent put piece on " + row + " " + column + " - DEFEAT!");
                game.report.putMoves(game.allMoves);
                game.report.putWinner(this);
                game.report.flushAll();

                String localPath = "C:/Users/Irina/Desktop/AdvancedProgramming/JavaLab10/Server10.2/";
                String remotePath = ".";

                Sftp ftp = new Sftp("127.0.0.1", 80, "root");

                ftp.upload(localPath + "Gomoku.html", remotePath);

                System.out.println("Finish. Game report created successfully");
            } else {
                //output.println("Moved " + row + " " + column);
                opponent.output.println("Opponent put piece on " + row + " " + column);
            }
            /* else if (game.isBoardFull()) {
                output.println("TIE");
                opponent.output.println("TIE");
            } */
        } catch (IllegalStateException e) {
            output.println("Exception " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            setup();
            processCommands();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (opponent != null && opponent.output != null) {
                opponent.output.println("Sorry, the other player left the game.");
                try {
                    opponent.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public PrintWriter getOutput() {
        return output;
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return mark == player.mark;
    }

}
