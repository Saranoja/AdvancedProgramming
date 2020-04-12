/**
 * Author: Calin Irina, I2E2
 */
package Concurrency;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose game type: progression or clique");
        String gameType = scanner.next();
        if (gameType.equals("progression")) {
            System.out.println("Number of tokens: ");
            int tokensNumber = scanner.nextInt();
            //generating an array of 20 tokens which will be on the board at first
            ArrayList<ProgressionToken> tokens = new ArrayList<>();
            var temp = IntStream.rangeClosed(1, tokensNumber).mapToObj(i -> new ProgressionToken(i)).toArray(ProgressionToken[]::new);
            for (ProgressionToken t : temp)
                tokens.add(t);
            System.out.println("Progression size: ");
            int progressionSize = scanner.nextInt(); //self-explanatory
            int players = 3; //number of players to play the game
            System.out.println("Timer (in seconds): ");
            int seconds = scanner.nextInt(); //how long should the game last
            Board board = new Board(tokens, progressionSize, players);
            //added timekeeper to the game
            Game game = new ProgressionGame(players, board, seconds);
            game.start();
        } else if (gameType.equals("clique")) {
            int cliqueSize = 0;
            System.out.println("Vertices number: ");
            int verticesNumber = scanner.nextInt();
            System.out.println("Number of players (all manual): ");
            int cliquePlayers = scanner.nextInt();
            System.out.println("Clique size: ");
            while(cliqueSize < 3) {
                cliqueSize = scanner.nextInt();
                if(cliqueSize < 3)
                    System.out.println("Clique size should be at least 3. Try again: ");
            }
            System.out.println("Timer (in seconds): ");
            int seconds = scanner.nextInt();
            Graph graph = new Graph(verticesNumber, cliqueSize, cliquePlayers);
            CliqueGame cliqueGame = new CliqueGame(cliquePlayers, verticesNumber, seconds, graph);
            cliqueGame.start();
        }
        else {
            System.out.println("Unknown type.");
        }
    }
}
