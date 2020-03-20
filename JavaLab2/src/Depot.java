import java.util.ArrayList;

public class Depot {
    private String name;
    private Vehicle[] vehicles;
    private Depot depot;

    public Depot(String name) {
        this.name = name;
    }

    /*public Depot(String name, ArrayList<Vehicle> vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle[] getVehicles() {
        //System.out.println(this.vehicles);
        return this.vehicles;
    }

    public void setVehicles(Vehicle... vehicles) {
        try {
            for (int i = 0; i < vehicles.length - 1; i++)
                for (int j = i + 1; j < vehicles.length; j++)
                    if (vehicles[i].equals(vehicles[j])) {
                        throw new Exception("Duplicate vehicle");
                    }
            this.vehicles = vehicles;
        } catch (Exception err) {
            System.out.println("Duplicate found in vehicles");
        }
        for(Vehicle v : vehicles) {
            v.setDepot(this);
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Depot)) return false;
        Depot depot = (Depot) o;
        return name.equals(depot.name);
    }

}
