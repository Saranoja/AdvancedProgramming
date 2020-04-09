/**
 * Author: Calin Irina, I2E2
 */
package Concurrency;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //generating an array of 20 tokens which will be on the board at first
        ArrayList<Token> tokens = new ArrayList<>();
        var temp = IntStream.rangeClosed(1, 30).mapToObj(i -> new Token(i)).toArray(Token[]::new);
        for (Token t : temp)
            tokens.add(t);
        int progressionSize = 4; //self-explanatory
        int players = 3; //number of players to play the game
        int seconds = 10; //how long should the game last
        Board board = new Board(tokens, progressionSize, players);
        //added timekeeper to the game
        Game game = new Game(players, board, seconds);
        game.start();
    }
}
