/**
 * @author: Calin Irina, I2E2
 */

import Gomoku.Player;

import java.net.Socket;

//this will just run the specific thread for a player (at least for now) - i still have to add "other commands"
//for now only STOP and MOVE work as commands

public class ClientThread implements Runnable {
    private Socket socket;
    private Player player;

    public ClientThread(Socket socket, Player player) {
        this.socket = socket;
        this.player = player;
        this.player.setSocket(socket);
    }

    @Override
    public void run() {
        System.out.println("Connected: " + socket);
        try {
            new Thread(player).start();
            /* var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String command = in.nextLine();
                if (command.toLowerCase().equals("stop")) {
                    socket.close();
                    System.out.println("Server stopped");
                    out.println("Server stopped");
                    break;
                } else {
                    System.out.println("Server received the request " + command);
                    out.println(command.toUpperCase());
                }
            } */
        } catch (Exception e) {
            System.out.println("Error:" + socket);
        }
    }
}
