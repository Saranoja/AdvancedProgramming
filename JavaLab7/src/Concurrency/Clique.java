package Concurrency;

import java.util.ArrayList;
import java.util.List;

public class Clique {
    private List<Integer> vertices;

    public Clique() {
        vertices = new ArrayList<>();
    }

    public List<Integer> getVertices() {
        return vertices;
    }

    public void setVertices(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public int getVerticesNr() {
        return vertices.size();
    }
}
