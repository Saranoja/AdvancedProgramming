package Concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CliquePlayer extends Player{
    protected Graph graph;
    protected List<Edge> extractedEdges;


    public CliquePlayer(int  playerID, Graph graph) {
        this.playerID = playerID;
        this.graph = graph;
        extractedEdges = Collections.synchronizedList(new ArrayList<>());
    }

    private int getUserToken() {
        Scanner scanner = new Scanner(System.in);
        boolean tokenExists = false;
        int tokenIndex = 0;
        while (graph.turn != playerID) {
        } //if it's not my turn, do nothing
        if (!graph.gameOver) {
            System.out.println("Available edges: " + graph.getEdges());
            System.out.println("[Player " + playerID + "] accumulated edges: " + this.extractedEdges);
            System.out.println("[Player " + playerID + "]: Pick a token by its index, starting from 1: ");
            while (!tokenExists && !graph.gameOver)
                try {
                    tokenIndex = Integer.parseInt(scanner.next());
                    --tokenIndex;
                    if (tokenIndex >= 0 && tokenIndex < graph.bound) {
                        tokenExists = true;
                    } else {
                        System.out.println("Edge with index " + (tokenIndex + 1) + " doesn't exist");
                        tokenExists = false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
        }
        return tokenIndex;
    }

    public int searchPosition(int firstVertex, int secondVertex) {
        int left = 0;
        int right = extractedEdges.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            Edge edge = extractedEdges.get(mid);
            if (edge.getFirstVertex() == firstVertex) {
                if (edge.getSecondVertex() == secondVertex) {
                    return mid;
                } else if (edge.getSecondVertex() > secondVertex) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (edge.getSecondVertex() > secondVertex) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public boolean potentialClique(List<Integer> vertices) {
        for (int i = 0; i < vertices.size() - 1; ++i) {
            for (int j = i + 1; j < vertices.size(); ++j) {
                int positionInArray = searchPosition(vertices.get(i), vertices.get(j));
                if (positionInArray == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public Clique getMaximumClique() {
        Clique clique = new Clique();
        Collections.sort(extractedEdges);
        int n = graph.getNumberOfVertices();
        int lastConfiguration = (1 << n);
        for (int i = 0; i < lastConfiguration; ++i) {
            List<Integer> vertices = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                if ((i & (1 << j)) != 0) {
                    vertices.add(j + 1);
                }
            }
            if (potentialClique(vertices)) {
                if (vertices.size() > clique.getVerticesNr()) {
                    clique.setVertices(vertices);
                }
            }
        }
        return clique;
    }

    public int getScore() {
        return getMaximumClique().getVerticesNr();
    }

    public synchronized void extractToken(int tokenIndex) {
        if (!graph.gameOver && graph.turn == this.playerID) {
            Edge edge = graph.getToken(this,tokenIndex);
        }
    }

    @Override
    public void run() {
        while (!graph.gameOver) {
            extractToken(getUserToken());
        }
    }

}
