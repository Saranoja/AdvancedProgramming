import java.util.ArrayList;
import java.util.Objects;

public class Client {
    private int order;
    private String name;

    public Client() {

    }

    public Client(String name, int time) {
        this.order = time;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int visitingTime) {
        this.order = visitingTime;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getName().equals(client.getName());
    }

}
