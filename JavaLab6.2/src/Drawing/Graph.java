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

    }
}
