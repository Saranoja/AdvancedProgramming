/**
 * Author: Calin Irina, I2E2
 */

package Drawing;

import java.awt.*;
import java.util.ArrayList;

public class Graph extends Polygon {
    ArrayList<Edge> edges;
    ArrayList<Vertex> vertices;

    public Graph(ArrayList<Edge> edges, ArrayList<Vertex> vertices) {
        this.edges = edges;
        this.vertices = vertices;
    }

    public void Format() {
        for (Edge edge : edges)
            for (Edge edge1 : edges) {
                if (!edge.equals(edge1)) {
                    System.out.println(vertices.get(edge.v1) + " and  " + vertices.get(edge.v2));
                    System.out.println(vertices.get(edge1.v1) + " and  " + vertices.get(edge1.v2));
                    if(vertices.get(edge.v1).x < vertices.get(edge.v2).x && vertices.get(edge1.v1).x<vertices.get(edge.v2).x
                    && vertices.get(edge.v1).y < vertices.get(edge1.v1).y && vertices.get(edge.v2).y>vertices.get(edge.v2).y)
                        System.out.println("Intersection");
                }
            }
    }
}
