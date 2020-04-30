import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;


public class GameClient {
    private static final String ip = "127.0.0.1";
    private static String command;

    private Socket socket;
    private Scanner in;
    private Scanner scanner;
    private PrintWriter out;
    private Board board;
    private int round;
    private int myIndex;

    public GameClient() {
        try {
            board = new Board(19);
            socket = new Socket(ip, 59186);
            scanner = new Scanner(System.in);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            String intro = in.nextLine();
            System.out.println(intro);
            String waiting = in.nextLine();
            System.out.println(waiting);
            //System.out.println("Player " + intro.substring(8));
            myIndex = Integer.parseInt(intro.substring(8));
            if (myIndex == 1)
                round = 1;
            else round = 0;
            String turn = in.nextLine();
            System.out.println(turn);
            play();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void play() {
        while (scanner.hasNextLine()) {

            String command = scanner.nextLine();
            out.println(command);

            if (command.toLowerCase().equals("stop"))
                break;

            //String myMoveFeedback = in.nextLine();
            //System.out.println(myMoveFeedback);

            String response = in.nextLine();
            System.out.println(response);

            if (!response.equals("Exception Cell already occupied")) {
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
            }

            for (int i = 0; i < 19; ++i) {
                for (int j = 0; j < 19; ++j)
                    System.out.print(board.getBoard()[i][j] + " ");
                System.out.println();
            }


            if (response.endsWith("VICTORY!") || response.endsWith("DEFEAT!"))
                break;

            if (response.equals("Sorry, the other player left the game."))
                break;

        }
    }
}