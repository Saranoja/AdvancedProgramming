package KnapsackProblem;//Author: Calin Irina, I2E2
import java.util.Comparator;

public class SortByName implements Comparator<Item> {
    public int compare(Item a, Item b) {
        if (a.getName().compareTo(b.getName()) > 0) return 1;
        else if (a.getName() == b.getName()) return 0;
        else return -1;
    }
}
