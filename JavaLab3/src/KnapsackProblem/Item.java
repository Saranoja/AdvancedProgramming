package KnapsackProblem;

//Author: Calin Irina, I2E2
public interface Item {
    String getName();

    double getValue();

    double getWeight();

    default double profitFactor() {
        return getValue() / getWeight();
    }
}