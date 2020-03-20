import java.util.*;
import java.lang.*;
import java.io.*;

class ShortestPath {
    // find the vertex with minimum cost value from the set of vertices not yet included in MST
    static final int V = 9;

    int mincostance(int cost[], Boolean visited[]) {
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (visited[v] == false && cost[v] <= min) {
                min = cost[v];
                min_index = v;
            }
        return min_index;
    }

    void printSolution(int cost[], int n) {
        System.out.println("Client   Tour cost");
        for (int i = 1; i < V; i++)
            System.out.println(i + " \t\t " + cost[i]);
    }
    
    void Dijkstra(int graph[][], int sourceVertex) {
        int u, copy=0;
        System.out.print("Clients in order: ");
        int cost[] = new int[V]; // the min cost from sourceVertex to i = output array

        // visited[i] will be true if the vertex i is included in MST or min cost from sourceVertex to i is finalized
        Boolean visited[] = new Boolean[V];
        
        for (int i = 0; i < V; i++) {
            cost[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // cost of source vertex from itself is always 0
        cost[sourceVertex] = 0;

        // Find "cheapest" path for all vertices 
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum cost vertex from the set of vertices
            // not yet processed. u is always equal to sourceVertex in first 
            // iteration.
            u = mincostance(cost, visited);

            // Mark the picked vertex as processed 
            visited[u] = true;
            if(graph[copy][u]!=-1)
                System.out.print(u + " -> ");
            else
            {
                System.out.println(" ");
                System.out.print(u + " -> ");
            }

            // Update cost of the adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                // Update cost[v] only if v is not in visited, there is an
                // edge from u to v and the total weight of the path from sourceVertex to
                // v through u is smaller than current value of cost[v] - bascially Dijkstra
                if (!visited[v] && graph[u][v] != -1 &&
                        cost[u] != Integer.MAX_VALUE && cost[u] + graph[u][v] < cost[v]) {
                    cost[v] = cost[u] + graph[u][v];
                }
            }
            copy=u;
        }
        u = mincostance(cost, visited);
        System.out.print(u);
        System.out.println("");
        printSolution(cost, V);
    }
}