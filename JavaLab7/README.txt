Concurrent progression game:
(Note: I have implemented the game for as many players as wanted, with whatever number of tokens/edges)

*Update for the bonus part:
Implemented the Clique Game, which, instead of the board, uses a complete graph. Tokens are now edges.
The threading system is the same as for the optional part: using a semaphor, each player waits for his turn.
The first player who gets a clique of the required size, wins. If time's up, the player with the largest
clique wins. All players are manual this time.
Note: getMaximumClique() and other helping methods were inspired from my research on the subject.
Note2: I have now arranged the code so the user can pick all the variables himself, such as the game type,
players number, game size etc.

////////////////////////////////////////////////////////////////////////////////////////////////
*Update for the optional part:
Now the Board class works based on a semaphor which notifies the players whether it's their turn or not.
There are 3 types of players extending the Player class: random, smart and manual. Each of them has its own "strategy".
There's now also a daemon timekeeper, which will stop the game once a given number of seconds have passed.
If the time is up and none of the players got the required progression, the one with the largest progression wins.
(Although the top is done anyway in case of expanding the game and playing more rounds on points).
Note: Optional-Case1.png and Optional-Case1-2.png show the behaviour of the app when there is a winner.
Optional-Case2.png illustrates what happens when the time is up and the game is stopped.

////////////////////////////////////////////////////////////////////////////////////////////////
Token class: provides a number from 1 to a given n - which represents the (shared) buffer part
Board class: provides a token list, together with the game ending condition and the critical section
Player class (implements Runnable): each player has its own ID to be identified with. run() method consists of
extracting a random token from the board (the ExtractToken() method - which applies as long as the game is not
over and only if the Board resource is available) - ExtractToken() is synchronized since it makes a call 
to Board's method getToken(), which was synchronized in the first place. This is where, once a player is in,
it marks the resource as unavailable for the other players. There is also another sleep() call, just to make sure
all the players had a chance to extract a token before the next round (in case of more than 2 players for instance).
Other methods from the Player class are used in order to check the existence of any arithmetic progression of 
the given size in the player's extracted tokens. 
Note: Due to the sensitivity of the timer, there is a possibility for more than one player to win 
(i.e. the token which was withdrawn by the last player makes him a winner as well). Considering this, the console
will still specify what the rest of the players extract, even after a first winner is found.
Note2: I have also added a screenshot of an example.