/**
 * @Author: Calin Irina, I2E2
 */

package Concurrency;

public class Edge implements Comparable {
    private int firstVertex;
    private int secondVertex;

    public Edge(int firstVertex, int secondVertex) {
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    public int getFirstVertex() {
        return firstVertex;
    }

    public void setFirstVertex(int firstVertex) {
        this.firstVertex = firstVertex;
    }

    public int getSecondVertex() {
        return secondVertex;
    }

    public void setSecondVertex(int secondVertex) {
        this.secondVertex = secondVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return firstVertex == edge.firstVertex &&
                secondVertex == edge.secondVertex;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        if (o == null || getClass() != o.getClass()) {
            return 1;
        }
        Edge edge = (Edge) o;
        if (firstVertex == edge.firstVertex) {
            return Integer.compare(secondVertex, edge.secondVertex);
        }
        return Integer.compare(firstVertex, edge.firstVertex);
    }

    @Override
    public String toString() {
        return "{" + firstVertex + ", " + secondVertex + "}";
    }
}
