package KnapsackProblem;//Author: Calin Irina, I2E2
import java.util.*;

public class Knapsack {
    private double capacity;
    private List<Item> items = new ArrayList<>();

    public Knapsack() {
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void decreaseCapacity(Item item) {
        this.capacity -= item.getWeight();
    }

    public Knapsack(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        List<String> names = new ArrayList<String>(items.size());
        int totalWeight = 0;
        int totalValue = 0;
        for (Item i : this.getItems()) {
            names.add(i.getName());
            totalValue += i.getValue();
            totalWeight += i.getWeight();
        }
        return "Selected items: " + names + " (total weight = " + totalWeight + ", total value= " + totalValue + ")";
    }
}
