import java.lang.reflect.Array;
import java.util.ArrayList;
import java.lang.Object;

public class Main {

    public static void main(String[] args) {
        Problem pb = new Problem();
        Client c1 = new Client();
        c1.setName("Client 1");
        c1.setOrder(1);
        //System.out.println(c1.getName());
        Client c2 = new Client("Client 2", 1);
        Client c3 = new Client("Client 3", 7);
        Client c4 = new Client("Client 4", 6);
        Client c5 = new Client("Client 5", 1);
        Client c6 = new Client("Client 6", 5);
        Client c7 = new Client("Client 7", 5);
        Client c8 = new Client("Client 8", 3);
        //System.out.println(c2);
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Car("V1");
        vehicles[1] = new Drone("V2");
        vehicles[2] = new Truck("V3");
        Depot d1 = new Depot("Depot 1");
        d1.setVehicles(vehicles[0], vehicles[1]);
        Depot d2 = new Depot("Depot 2");
        Depot d3 = new Depot("Depot 3");
        d2.setVehicles(vehicles[2]);
        pb.setClients(c1, c2, c3, c4, c5, c6, c7, c8);
        pb.setDepots(d1, d2, d3);
        System.out.println(pb);
        Solution s = new Solution(pb);
        s.getSolution(pb);
        System.out.println("-----------------");
        int graph[][] = new int[][]{{-1, 4, -1, -1, -1, -1, -1, -1, 8},
                {-1, -1, 8, -1, -1, -1, -1, -1, 11},
                {-1, -1, -1, 3, 2, -1, -1, 1, -1},
                {-1, -1, -1, -1, -1, 2, 1, -1, -1},
                {-1, -1, -1, -1, -1, -1, 1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, 3, -1},
                {-1, -1, 2, -1, -1, -1, 1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, 1, -1}};
        ShortestPath path = new ShortestPath();
        path.Dijkstra(graph, 0);
    }
}
