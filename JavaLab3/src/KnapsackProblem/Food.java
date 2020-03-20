package KnapsackProblem;//Author: Calin Irina, I2E2

public class Food implements Item {
    private String name;
    private double weight; // → getWeight, getValue

    public Food(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getValue() {
        return this.weight*2;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public double profitFactor() {
        return this.getValue()/this.getWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return Double.compare(food.getWeight(), getWeight()) == 0 &&
                getName().equals(food.getName());
    }

    @Override
    public String toString() {
        return name +
                ", weight = " + this.getWeight() +
                ", value = " + this.getValue() +
                " (profit factor = " + this.profitFactor() + ")";
    }

}
