/**
 * @author: Calin Irina, I2E2
 */

import Gomoku.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//this will just run the specific thread for a player (at least for now) - i still have to add "other commands"
//for now only STOP and MOVE work as commands

public class ClientThread implements Runnable {
    private Socket socket;
    private Player player;
    private String command;

    public ClientThread(Socket socket, Player player) {
        this.socket = socket;
        this.player = player;
        this.player.setSocket(socket);
    }

    @Override
    public void run() {
        System.out.println("Connected: " + socket);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello. Choose from commands: start - move - stop");
            command = "init";

            while (!command.equals("start")) {
                command = in.readLine();
                System.out.println("Received the command " + command);
                if (command.toLowerCase().equals("start")) {
                    new Thread(player).start();
                } else if (command.toLowerCase().equals("stop")) {
                    out.println("You cannot stop the game unless you have started, duuuh");
                    System.out.println("Client wanted to stop game");
                    //socket.close();
                    //break;
                } else {
                    out.println("Unknown, try again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* finally {
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        } */
    }
}
