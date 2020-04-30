Networking - Gomoku

*Update for the optional part:
I have now created a thread pool so that the server can hold more matches in parallel
(at most 200 threads, actually)
For now, most things happen in the Player class - the information exchange with the 
clients is done so that any match is synchronized
The server will send an intro message to each player connecting to it, depending on
the index of the player (1 or 2). Player 1 is the first one to place its number on the
board. If a certain cell is already occupied, an exception is thrown so the player will
choose again.
What happens (at least for now) when a player wants to place a number although it's not
their turn? Well... there's a little bug... (somehow). It's not that terrible though.
Because there's a scanner on the client side, we cannot force the user not to write 
anything in the console. What happens is that his commands get collected in
a queue and will be executed in order (that's because the read function is blocking and so
withouth the opponent's move, a player cannot read the thrown exception) - since the server 
tries to keep the game synchronized. So it's not such a great idea to abuse, the game would
get super delayed. (I'm working on a fix)
However, when a winning condition is fullfilled, the game stops and both players are notified
regarding the winner.
If a player leaves a match, the other one gets notified.
//last bullet is still in progress, I'm doing some research

Compulsory:
I have basically just respected the instructions given on the lab page