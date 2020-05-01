/**
 * @author: Calin Irina, I2E2
 */

import Gomoku.Board;
import Gomoku.Game;
import Gomoku.Player;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

//singleton class which creates a ThreadPool in order to support more Gomoku matches in parallel
//a new game is created for every 2 players that join the game
//a game is therefore shared by 2 players as a common resource for 2 threads

public class GameServer {

    private static GameServer ourInstance = new GameServer();
    private PrintWriter out;
    private InputStreamReader in;
    private String command;
    private static final int port = 59186;

    public static GameServer getInstance() {
        return ourInstance;
    }

    private GameServer() {
        try (var listener = new ServerSocket(port)) {
            System.out.println("The game server is running...");
            var pool = Executors.newFixedThreadPool(200); //employ a thread pool
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