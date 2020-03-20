public class Drone extends Vehicle {
    public Drone() {
    }

    public Drone(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
