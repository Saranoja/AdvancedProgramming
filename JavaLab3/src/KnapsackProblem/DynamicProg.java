package KnapsackProblem;//Author: Calin Irina, I2E2

import java.util.List;

public class DynamicProg implements Algorithm {
    private Knapsack knapsack;
    private List<Item> items;

    public DynamicProg(Knapsack knapsack, List<Item> items) {
        this.knapsack = knapsack;
        this.items = items;
    }

    public void FillKnapsack() {
        //respects the algorithm described in the slides - we use a bottom-up approach to calculate the optimal solution using the matrix
        //runs in O(nbItems*capacity)
        //each item can be taken or not taken
        //when there are i items to choose, m[i][w] is the optimal weight when the maximum weight of the knapsack is w
        int capacity = (int) knapsack.getCapacity();
        int nbItems = items.size();
        double[][] m = new double[nbItems + 1][capacity + 1];

        for (int j = 0; j <= capacity; j++) {
            m[0][j] = 0;
        }

        for (int i = 1; i <= nbItems; i++) {
            for (int w = 1; w <= capacity; w++) {
                //with the weight limit w, the optimal selections among items {1, 2, ..., i – 1, i} to have the largest value will have two possibilities:
                //if item i is not selected, m[i][w] is the maximum possible value by selecting among items {1, 2, ..., i – 1} with weight limit of w
                //if item i is selected (of course we only consider this case when weight[i] <= w) then m[i][w] is equal to value[i] + the maximum value that can be obtained by selecting among items {1, 2, ..., i – 1} with the limit (w – weight[i])
                if (items.get(i - 1).getWeight() > w) {
                    m[i][w] = m[i - 1][w];
                } else {
                    m[i][w] = Math.max(m[i - 1][w], m[i - 1][w - (int) items.get(i - 1).getWeight()] + items.get(i - 1).getValue());
                }
            }
        }
        System.out.println("[DP] The maximum profit you can get is " + m[nbItems][capacity] + " with the following items:");

        do {
            if (m[nbItems][capacity] != m[nbItems - 1][capacity]) {
                System.out.println("\t" + items.get(nbItems-1));
                capacity = capacity - (int) items.get(nbItems - 1).getWeight();
            }
            nbItems--;
        } while (nbItems > 0);
    }
}
