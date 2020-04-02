/**
 * Author: Calin Irina, I2E2
 */
package Concurrency;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //generating an array of 20 tokens which will be on the board at first (+ 2 blank tokens later)
        ArrayList<Token> tokens = new ArrayList<>();
        var temp = IntStream.rangeClosed(1, 20).mapToObj(i -> new Token(i)).toArray(Token[]::new);
        for (Token t : temp)
            tokens.add(t);
        Token blank1 = new Token(777);
        Token blank2 = new Token(777);
        //tokens.add(blank1);
        //tokens.add(blank2);
        int progressionSize = 3; //self-explanatory
        Board board = new Board(tokens,progressionSize);
        int players = 2; //number of players to play the game
        Game game = new Game(players,board);
        game.Start();
    }
}
