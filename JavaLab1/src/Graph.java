import java.util.Queue;
import java.util.LinkedList;

public class Graph {
    public static int[] BFS(int[][] adjacencyMatrix, int vertexCount, int startVertex) {
        // Result array.
        int[] mark = new int[vertexCount];

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(startVertex);
        mark[startVertex] = 1;

        while (!queue.isEmpty()) {
            Integer current = queue.remove();

            for (int i = 0; i < vertexCount; ++i)
                if (adjacencyMatrix[current][i] == 1 && mark[i] == 0) {
                    mark[i] = 1;
                    queue.add(i);
                }
        }

        return mark;
    }


    public static void main(String[] args) {
        // Given adjacencyMatrix[x][y] if and only if there is a path between x and y.
        int[][] adjacencyMatrix = new int[][]
                {
                        {1, 1, 1, 1, 0, 0, 0},
                        {1, 1, 1, 1, 0, 0, 0},
                        {1, 1, 1, 1, 0, 0, 0},
                        {1, 1, 1, 1, 1, 0, 0},
                        {1, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0, 1, 1}
                };
        // Mark[i] is true if and only if i belongs to the same connected component as givenVertex vertex does.

        int noVertices = 0;
        int[] connected = new int[7];
        int[] mark = new int[7];
        do {
            if (connected[noVertices] == 0) {
                mark = BFS(adjacencyMatrix, 7, noVertices);

                System.out.print("Connected component: " + noVertices + " ");

                for (int j = 0; j < 7; ++j)
                    if (mark[j] == 1 && j != noVertices) {
                        System.out.print(j + " ");
                        connected[j] = 1;
                    }
                System.out.println("");
            }
            noVertices++;
        } while (noVertices < 7);
    }
}
