package Concurrency;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Edge> edges;
    private int numberOfVertices;
    public volatile boolean gameOver = false;
    public int round = 0;
    public int nrPlayers;
    public volatile int turn = 1;
    int cliqueSize;
    int bound;

    public Graph(int n, int cliqueSize, int nrPlayers) {
        numberOfVertices = n;
        this.cliqueSize = cliqueSize;
        this.nrPlayers = nrPlayers;
        edges = new ArrayList<>();
        for (int i = 1; i < n; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                edges.add(new Edge(i, j));
            }
        }
        bound = n*(n-1)/2;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public synchronized Edge getToken(CliquePlayer player, int edgeIndex) {
        Edge chosen = new Edge(0,0);
        while (turn != player.playerID) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(!gameOver && bound>0) {
            chosen = edges.get(edgeIndex);
            player.extractedEdges.add(chosen);
            edges.remove(chosen);
            --bound;
            System.out.println("Player " + player.playerID + " extracted edge [" + chosen.getFirstVertex() + ", " +
                    chosen.getSecondVertex() + "]");
            System.out.println("");
            if(player.getScore()==cliqueSize)
            {
                System.out.println("Player " + player.playerID + " has won.");
                System.out.println("Winning combination: " + player.extractedEdges);
                gameOver = true;
                notifyAll();
            }
        }
        if (bound == 0)
            gameOver = true;
        ++round;
        turn = round % nrPlayers + 1;
        notifyAll();
        return chosen;
    }
}
