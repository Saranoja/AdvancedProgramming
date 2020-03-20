package KnapsackProblem;//Author: Calin Irina, I2E2
import java.util.Objects;

public class Book implements Item {
    private String name;
    private int pageNumber; //→ getWeight
    private double value;

    public Book(String name, int pageNumber, double value) {
        this.name = name;
        this.pageNumber = pageNumber;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public double getWeight() {
        return this.pageNumber/100;
    }

    @Override
    public double profitFactor() {
        return this.getValue()/this.getWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getName(), book.getName());
    }

    @Override
    public String toString() {
        return name +
                ", weight = " + this.getWeight() +
                ", value = " + value +
                " (profit factor = " + this.profitFactor() + ")";
    }
}
