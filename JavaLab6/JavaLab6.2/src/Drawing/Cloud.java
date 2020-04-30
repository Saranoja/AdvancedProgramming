/**
 * Author: Calin Irina, I2E2
 */

package Drawing;

import java.awt.*;

public class Cloud extends Polygon {

    public Cloud(int x0, int y0, int radius, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(50, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        g2.setColor(new Color(131, 186,216));
        g2.fillOval(x0-radius, y0, radius*2, radius*2);
        g2.setColor(new Color(107, 156, 182));
        g2.fillOval(x0, y0-radius/4, radius/4*5, radius/4*5);
        g2.setColor(new Color(115, 164, 188));
        g2.fillOval(x0+radius/2*3, y0+radius/2, radius/2*3, radius/2*3);

        g2.setColor(new Color(148, 216, 248));
        g2.fillOval(x0+radius/2, y0-radius, radius*2, radius*2);
        g2.setColor(new Color(134, 195, 225));
        g2.fillOval(x0+radius/2, y0+radius/2, radius*3/2, radius*3/2);
    }
}
