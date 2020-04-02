Concurrent progression game:
(Note: I have implemented the game for as many players as wanted, with whatever number of tokens)
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