/**
 * Author: Calin Irina, I2E2
 */

package Drawing;

import java.awt.*;

public class Spectre extends Polygon {
    public Spectre(int x0, int y0, int radius, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(176, 0, 255));
        g2.fillOval(x0, y0, radius*6, radius*6);
        g2.setColor(new Color(67, 0, 254));
        g2.fillOval(x0, y0, radius*5, radius*5);
        g2.setColor(new Color(36, 108, 255));
        g2.fillOval(x0, y0, radius*4, radius*4);
        g2.setColor(new Color(0, 208, 0));
        g2.fillOval(x0, y0, radius*3, radius*3);
        g2.setColor(new Color(253, 255, 0));
        g2.fillOval(x0, y0, radius*2, radius*2);
        g2.setColor(new Color(255, 108, 0));
        g2.fillOval(x0, y0, radius, radius);
        g2.setColor(new Color(255, 22, 0));
        g2.fillOval(x0, y0, radius/2, radius/2);
    }
}
