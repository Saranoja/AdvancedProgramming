package KnapsackProblem;//Author: Calin Irina, I2E2

public class Weapon implements Item  {
    private Type type;
    private double weight;
    private double value;
    public double profitFactor() {
        return 2;
    }

    public Weapon(Type type, double weight, double value) {
        this.type = type;
        this.weight = weight;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return type.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weapon)) return false;
        Weapon weapon = (Weapon) o;
        return getType() == weapon.getType();
    }

    @Override
    public String toString() {
        return type + ", weigth = " + this.weight + ", value= " + this.value + " (profit factor = " + this.profitFactor() + ")";
    }
}
