package KnapsackProblem;//Author: Calin Irina, I2E2
import java.util.Comparator;

public class SortByWeight implements Comparator<Item> {
    public int compare(Item a, Item b) {
        if (a.getWeight() > b.getWeight()) return 1;
        else if (a.getWeight() == b.getWeight()) return 0;
        else return -1;
    }
}