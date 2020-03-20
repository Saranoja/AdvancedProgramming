import java.util.Objects;
/**Instead of using an enum, create dedicated classes for cars, trucks and drones. Vehicle will become abstract.
 * Each vehicle belongs to a single depot. It starts from there and it may return there at any time.
 * Each vehicle will perform a single tour, consisting of one or more trips with strictly ascending visiting times, starting from its depot and ending in it.
 */

public abstract class Vehicle {

    public String name;
    private Depot depot;
    private VehicleType type;

    public Vehicle() { }

    public Vehicle(String name) {
        this.name = name;
    }

    public Depot getDepot() {
        return this.depot;
    }

    void setDepot(Depot depot) {
        this.depot = depot;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return getName().equals(vehicle.getName());
    }

}
