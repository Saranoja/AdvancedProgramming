package KnapsackProblem;//Author: Calin Irina, I2E2
import java.util.Comparator;

public class SortByValue implements Comparator<Item> {
    public int compare(Item a, Item b) {
        if (a.getValue() < b.getValue()) return 1;
        else if (a.getValue() == b.getValue()) return 0;
        else return -1;
    }
}