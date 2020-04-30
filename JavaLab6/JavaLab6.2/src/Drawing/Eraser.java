/**
 * Author: Calin Irina, I2E2
 */
package Drawing;

import java.awt.*;

public class Eraser extends Polygon {
    public Eraser(int x0, int y0, int radius) {
        double x;
        double y;
        double angle = 0;
        double angleAddition = 2 * Math.PI / (100 * radius);
        while (angle < 2 * Math.PI) {
            x = x0 + radius * Math.sin(angle);
            y = y0 + radius * Math.cos(angle);
            angle += angleAddition;
            this.addPoint((int) x, (int) y);
        }
    }
}
