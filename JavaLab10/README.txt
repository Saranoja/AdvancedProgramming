Networking - Gomoku

*Update for the optional part:
I have now created a thread pool so that the server can hold more matches in parallel
(at most 200 threads, actually)
For now, most things happen in the Player class - the information exchange with the 
clients is done so that any match is synchronized
The server will send an intro message to each player connecting to it.
Based on common sense, you are not allowed to stop a game or to place a piece on the board
unless you have first joined a match.
Player 1 is the first one to place its number on the board. If a certain cell is already 
occupied, an exception is thrown so the player will choose again. 
If a player tries to place a number although it's not his turn, he will also be notified. 
Same if the command is unknown.
When a winning condition is fullfilled, the game stops and both players are notified
regarding the winner.
If a player leaves a match, the other one gets notified.

For the client side I've restructured everything, so that reading from the input stream doesn't
block the whole game. There's now a thread for writing and a separate one for reading so that
a user can write commands and receive notifications WHILE waiting for the opponent's move.
(This was done just for a "better UX" - logically, I am not supposed to move unless it is my
turn... but what if I try to do so? I should be notified that I have to wait etc.)

I have also generated a report of the game using Freemarker and uploaded the html on a web server (localhost)
using Jsch of JCraft
//also I'm working on implementing more commands (create, join)

Compulsory:
I have basically just respected the instructions given on the lab page