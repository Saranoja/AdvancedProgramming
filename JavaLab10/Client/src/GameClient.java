/**
 * @author: Calin Irina, I2E2
 */

import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;

//for now: client reads the intro and then it exchanges information with the server -
// first writes the command and then reads the answer

public class GameClient {
    private static final String ip = "127.0.0.1";
    private static final int port = 59186;
    private static String command;

    private Socket socket;
    private Scanner in;
    private Scanner scanner;
    private PrintWriter out;
    private Board board;
    private int round;
    private int myIndex;
    private String response;

    public GameClient() {
        try {
            board = new Board(19);
            socket = new Socket(ip, port);
            scanner = new Scanner(System.in);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            String response = in.nextLine();
            System.out.println(response);

            while (!response.startsWith("WELCOME")) {
                command = scanner.nextLine();
                out.println(command);
                response = in.nextLine();
                System.out.println(response);
                //if(response.toLowerCase().equals("server stopped"))
                //break;
            }

            if (response.toUpperCase().startsWith("WELCOME")) {
                myIndex = Integer.parseInt(response.substring(8));

                response = in.nextLine();
                System.out.println(response);

                response = in.nextLine();
                System.out.println(response);

                //for the second player only
                if (response.startsWith("Opponent")) {
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

                play();
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } /* finally {
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } */
    }

    public void play() {
        ReadingThread read = new ReadingThread(in, board, myIndex);
        WritingThread write = new WritingThread(scanner, out, board, read);
        new Thread(read).start();
        new Thread(write).start();
        return;
    }
}