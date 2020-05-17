/**
 * Author: Calin Irina, I2E2
 */

package Drawing;

import java.awt.*;
import java.util.Objects;

public class Edge extends Polygon {
    int v1;
    int v2;
    Vertex V1;
    Vertex V2;

    public Edge(Vertex v1, Vertex v2) {
        this.V1 = v1;
        this.V2 = v2;
    }

    public Edge(int x0, int y0, int x1, int y1, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(255, 111, 91));
        g2.setStroke(new BasicStroke(5));
        if (x0 < x1 && y0 < y1)
            g2.drawLine(x0 + 10, y0 + 10, x1 - 10, y1 - 10);
        if (x0 < x1 && y0 > y1)
            g2.drawLine(x0 + 10, y0 - 10, x1 - 10, y1 + 10);
        if (x0 > x1 && y0 < y1)
            g2.drawLine(x0 - 10, y0 + 10, x1 + 10, y1 - 10);
        if (x0 > x1 && y0 > y1)
            g2.drawLine(x0 - 10, y0 - 10, x1 + 10, y1 + 10);
    }

    public void drawMe(int x0, int y0, int x1, int y1, Graphics2D g) {
        System.out.println("Redrawing edge");
        //Graphics2D g2 = (Graphics2D) g;
        g.setColor(new Color(92, 200, 104));
        g.setStroke(new BasicStroke(5));
        if (x0 < x1 && y0 < y1)
            g.drawLine(x0 + 10, y0 + 10, x1 - 10, y1 - 10);
        if (x0 < x1 && y0 > y1)
            g.drawLine(x0 + 10, y0 - 10, x1 - 10, y1 + 10);
        if (x0 > x1 && y0 < y1)
            g.drawLine(x0 - 10, y0 + 10, x1 + 10, y1 - 10);
        if (x0 > x1 && y0 > y1)
            g.drawLine(x0 - 10, y0 - 10, x1 + 10, y1 + 10);
    }

    public void setVertices(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return v1 == edge.v1 &&
                v2 == edge.v2;
    }
}
