import com.sun.jdi.request.DuplicateRequestException;

import java.util.Arrays;
import java.lang.*;

public class Problem {
    private Depot[] depots;
    private Client[] clients;

    public Problem() {

    }

    public Problem(Depot[] depots, Client[] clients) {
        this.depots = depots;
        this.clients = clients;
    }

    public Depot[] getDepots() {
        return depots;
    }

    public void setDepots(Depot... depots) {
        try {
            for (int i = 0; i < depots.length - 1; i++)
                for (int j = i + 1; j < depots.length; j++)
                    if (depots[i].equals(depots[j])) {
                        throw new Exception("Duplicate depot");
                    }
            this.depots = depots;
        } catch (Exception err) {
            System.out.println("Duplicate found in depot");
        }
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client... clients) {
        try {
            for (int i = 0; i < clients.length - 1; i++)
                for (int j = i + 1; j < clients.length; j++)
                    if (clients[i].equals(clients[j])) {
                        throw new Exception("Duplicate client");
                    }
            this.clients = clients;
        } catch (Exception err) {
            System.out.println("Duplicate found in clients");
        }
    }

    public static <T> T[] concatAll(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0,  result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    /** @return an array of all the vehicles, form all depots.*/

    public Vehicle[] getVehicles() {
        //tester
        Vehicle[] vehicles = concatAll(depots[0].getVehicles(),depots[1].getVehicles());
        return vehicles;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "depots=" + Arrays.toString(depots) + ", vehicles=" + Arrays.toString(getVehicles()) +
                ", clients=" + Arrays.toString(clients) +
                '}';
    }
}
