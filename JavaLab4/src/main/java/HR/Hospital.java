/**
 * @author : Calin Irina, I2E2
 */
package HR;

public class Hospital implements Type {
    private String name;
    private int capacity;
    Hospital a;

    public Hospital() {

    }

    public Hospital(String name) {
        this.name = name;
    }

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public int compareTo(Hospital b) {
        if (a.getName().compareTo(b.getName()) > 0) return 1;
        else if (a.getName() == b.getName()) return 0;
        else return -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void decreaseCapacity() {
        this.capacity -= 1;
    }

    @Override
    public String toString() {
        return name;
    }
}
