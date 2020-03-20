package KnapsackProblem;//Author: Calin Irina, I2E2
import java.util.Collections;
import java.util.List;

public class Greedy implements Algorithm {
    private Knapsack knapsack;
    private List<Item> items;

    public Greedy(Knapsack knapsack, List<Item> items) {
        this.knapsack = knapsack;
        this.items = items;
    }

    public void FillKnapsack() {
        //sorts the given items array and adds stuff to the knapsack according to this order, until it reaches the maximum capacity
        //runs in O(nbItems) - theoretically faster than DP on larger problems
        Collections.sort(items, new SortByValue());
        Item[] itemsArray = new Item[items.size()];
        itemsArray = items.toArray(itemsArray);
        for (Item i : itemsArray) {
            if (knapsack.getCapacity() - i.getWeight() >= 0) {
                knapsack.addItem(i);
                knapsack.decreaseCapacity(i);
            }
        }
        System.out.println(knapsack.toString());
    }
}
