import Gomoku.Board;
import Gomoku.Game;
import Gomoku.Player;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * A simple TCP server which creates a thread for each connected client so it runs concurrently
 */
public class GameServer {

    private static GameServer ourInstance = new GameServer();
    private PrintWriter out;
    private InputStreamReader in;
    private String command;

    public static GameServer getInstance() {
        return ourInstance;
    }

    private GameServer() {
        try (var listener = new ServerSocket(59186)) {
            System.out.println("The game server is running...");
            var pool = Executors.newFixedThreadPool(20); //employ a thread pool
            // and use an executor service to manage the threads
            while (true) {
                Board board = new Board(19);
                Game gomoku = new Game(board);
                pool.execute(new ClientThread(listener.accept(), new Player(1, gomoku)));
                pool.execute(new ClientThread(listener.accept(), new Player(2, gomoku)));
                //pool.execute(new ClientThread(listener.accept()));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}