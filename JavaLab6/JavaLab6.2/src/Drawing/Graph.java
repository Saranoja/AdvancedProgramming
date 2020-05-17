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

    public Vertex getVertexForIndex(int index) {
        for(Vertex vertex : vertices) {
            if(vertex.index==index)
                return vertex;
        }
        return null;
    }

    public void drawEdgeForIndexes(int index1, int index2, Graphics2D graphics) {
        Vertex v1 = getVertexForIndex(index1);
        Vertex v2 = getVertexForIndex(index2);
        Edge edge = new Edge(v1,v2);
        edge.drawMe(v1.x,v1.y,v2.x,v2.y,graphics);
    }

    public void Format(Graphics graphics) {
        int aux;
        for (Edge edge : edges)
            for (Edge edge1 : edges) {
                if (!edge.equals(edge1)) {
                    //System.out.println(vertices.get(edge.v1) + " and  " + vertices.get(edge.v2));
                    //System.out.println(vertices.get(edge1.v1) + " and  " + vertices.get(edge1.v2));
                    /* if (edge.V1.x < edge1.V2.x && edge.V2.x > edge1.V1.x) {
                        aux = edge.V1.x;
                        edge.V1.x = edge1.V2.x;
                        edge1.V2.x = aux; */
                    if (vertices.get(edge.v1).x < vertices.get(edge1.v1).x && vertices.get(edge.v1).x < vertices.get(edge1.v1).x) {
                        System.out.println("Found intersection: " + edge.v1 + " and " + edge1.v1 + " should be switched");
                        //aux = vertices.get(edge.v1).x;
                        //vertices.get(edge.v1).x = vertices.get(edge1.v1).x;
                        //vertices.get(edge1.v1).x = aux;
                        aux = vertices.get(edge.v1).index;
                        vertices.get(edge.v1).index = vertices.get(edge1.v1).index;
                        vertices.get(edge1.v1).index = aux;
                    }
                }
            }
    }
}
