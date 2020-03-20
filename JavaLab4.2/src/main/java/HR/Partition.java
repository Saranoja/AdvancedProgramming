/**
 * @author : Calin Irina, I2E2
 */
package HR;

public class Partition implements Type {
    private String name;
    private int capacity;
    Partition b;

    public Partition(String name) {
        this.name = name;
    }

    public Partition(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
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

    @Override
    public String toString() {
        return name;
    }
}
