package KnapsackProblem;

//Author: Calin Irina, I2E2
/* Note: Intuitively, for each of the n items, we have to make one decision, whether to
take that particular item or not. Thus any node must represent some item. Also,
we have a capacity bound on the knapsack weight, so we also need to maintain
a weight field in each node which represents the weight of the items included
on the path of reaching that particular node. Now comes the question of what
the edges shall represent. We might guess that the edges should represent the
value of that particular item. This edge shall represent the decision that we
took the particular item into our knapsack. So this edge would now be incident
on another level of the graph denoting the next item level, but the weight node
would be updated and hence, it would be incident accordingly on that particular
node of the next level which has the weight field as equal to the new updated
total weight. Also we might choose to not take the ith item with us. In this
case, the edge corresponding to this node would be incident on the next level
(the level for the next item) which has the weight field same as the previous
one.
In this particular graph basically, we need to find the solution for maximum
path length from the source to any done vertex (the n + 1th level vertex).
We already have shortest path finding algorithms for DAGs (Directed Acyclic
Graphs) using topological ordering. Thus convert this graph so that our aim
becomes computing the minimum length path. For this, make all edge weights
as negative of what they already are. Thus, now we can implement an already
known graph algorithm to get the solution for maximum knapsack value. The
answer to return would be negative of the minimum path length.*/
public class GraphModel {
    Problem p1;

    public GraphModel(Problem p1) {
        this.p1 = p1;
    }

    public static int getMinDistance(double distance[], Boolean chosen[], int V) {
        double minim = Integer.MAX_VALUE;
        int index = -1;

        for (int v = 0; v < V; v++)
            if (chosen[v] == false && distance[v] <= minim) {
                minim = distance[v];
                index = v;
            }

        return index;
    }

    public static double[] findShortestPath(double matrix[][], int src, int V) {

        double distance[] = new double[V];
        Boolean chosen[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
            chosen[i] = false;
        }
        distance[src] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = getMinDistance(distance, chosen, V);
            chosen[u] = true;
            for (int v = 0; v < V; v++)
                if (chosen[v] != true && matrix[u][v] != -1 && distance[u] != Integer.MAX_VALUE && distance[u] + matrix[u][v] < distance[v])
                    distance[v] = distance[u] + matrix[u][v];
        }
        return distance;
    }

    public int getRows() {
        int nbItems = p1.getItems().size();
        int rows = 1;
        while (nbItems != 1) {
            rows = (rows - 1) * 2 + 3;
            nbItems--;
        }
        return rows;
    }

    public int getColumns() {
        int columns = 0;
        int nbItems = p1.getItems().size();
        while (nbItems != 0) {
            columns = columns * 2 + 2;
            nbItems--;
        }
        columns++;
        return columns;
    }

    public double[][] getProfitMatrix() {
        int rows = this.getRows();
        int columns = this.getColumns();
        double[][] matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                matrix[i][j] = -1;
        int count = 1;
        int rownum = 0;
        while (count < columns) {
            matrix[rownum][count] = 0;
            rownum++;
            count += 2;
        }
        count = 2;
        rownum = 0;
        int targetObject = 0;
        matrix[rownum][count] = p1.getItems().get(targetObject).profitFactor();
        count += 2;
        rownum++;
        targetObject++;
        int quantity = 2;
        int copy = quantity;
        while (count < columns) {
            matrix[rownum][count] = p1.getItems().get(targetObject).profitFactor();
            count += 2;
            rownum++;
            copy--;
            if (copy == 0) {
                quantity *= 2;
                copy = quantity;
                targetObject++;
            }
        }
        return matrix;
    }

    private double[][] getWeightMatrix() {
        int rows = this.getRows();
        int columns = this.getColumns();
        double[][] matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                matrix[i][j] = -1;
        int count = 1;
        int rownum = 0;
        while (count < columns) {
            matrix[rownum][count] = 0;
            rownum++;
            count += 2;
        }
        count = 2;
        rownum = 0;
        int targetObject = 0;
        matrix[rownum][count] = p1.getItems().get(targetObject).getWeight();
        count += 2;
        rownum++;
        targetObject++;
        int quantity = 2;
        int copy = quantity;
        while (count < columns) {
            matrix[rownum][count] = p1.getItems().get(targetObject).getWeight();
            count += 2;
            rownum++;
            copy--;
            if (copy == 0) {
                quantity *= 2;
                copy = quantity;
                targetObject++;
            }
        }
        return matrix;
    }

    public void printMatrix() {
        double[][] matrix = getProfitMatrix();
        int rows = this.getRows();
        int columns = this.getColumns();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }

    public void getSolution() {
        double[][] profitMatrix = getProfitMatrix();
        double[][] weightMatrix = getWeightMatrix();
        int rows = this.getRows();
        int nbCols = 0;
        int colsBack = 0;
        int nbItems = p1.getItems().size();
        while (nbItems != 0) {
            colsBack = nbCols;
            nbCols = nbCols * 2 + 2;
            nbItems--;
        }
        colsBack++;
        nbCols -= colsBack;

        double[] profit = findShortestPath(profitMatrix, 0, rows).clone();
        double[] weight = findShortestPath(weightMatrix, 0, rows).clone();
        int total = 0;

        //for(double i:profit)
            //System.out.println(i);
        //for(double i:weight)
            //System.out.println(i);

        double max = -5;
        double actualWeight = 0;
        for (int i = 0; i < profit.length; i++) {
            if (profit[i] > max && weight[i] <= p1.getKnapsack().getCapacity()) {
                max = profit[i];
                //System.out.println(max);
                actualWeight = weight[i];
                total += profit[i] * weight[i];
            }
        }
        System.out.println("The maximum cumulated profit factor you can get is " + max + " with a total weight of " +
                (int) actualWeight);
    }
}