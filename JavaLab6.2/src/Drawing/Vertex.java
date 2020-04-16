/**
 * Author: Calin Irina, I2E2
 */

package Drawing;

import java.awt.*;

public class Vertex extends Polygon {
    public int x;
    public int y;
    public int index;
    public int degree = 0;

    public Vertex(int x0, int y0, int order) {
        this.x = x0;
        this.y = y0;
        this.index = order;
        double x;
        double y;
        double angle = 0;
        double angleAddition = 2 * Math.PI / (100 * 30);
        while (angle < 2 * Math.PI) {
            x = x0 + 30 * Math.sin(angle);
            y = y0 + 30 * Math.cos(angle);
            angle += angleAddition;
            this.addPoint((int) x, (int) y);
        }
    }

    public void increaseDegree() {
        this.degree++;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return index + "";
    }
}
