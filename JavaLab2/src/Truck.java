public class Truck extends Vehicle {
    public Truck() {
    }

    public Truck(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
