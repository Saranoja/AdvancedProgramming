/**
 * Author: Calin Irina, I2E2
 */
package Drawing;

import java.awt.*;
import javax.swing.*;

public class Flower extends Polygon {

    public Flower(int x0, int y0, int radius, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //stem
        g2.setStroke(new BasicStroke(50, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        //center
        g2.setColor(new Color(202, 102,1));
        g2.fillOval(x0-radius/4, y0-radius/4, radius*3/2, radius*3/2);

        int petalWidth = radius;
        int petalHeight = radius;
        g2.setColor(Color.yellow);
        g2.fillOval(x0-radius, y0, petalWidth, petalHeight);

        g2.setColor(new Color(252,194,1));
        g2.fillOval(x0-radius+radius/3, y0-radius+radius/3, petalWidth, petalHeight);

        g2.setColor(Color.yellow);
        g2.fillOval(x0, y0-radius, petalWidth, petalHeight);

        g2.setColor(new Color(252,194,1));
        g2.fillOval(x0+radius-radius/3, y0-radius+radius/3, petalWidth, petalHeight);

        g2.setColor(Color.yellow);
        g2.fillOval(x0+radius, y0, petalWidth, petalHeight);

        g2.setColor(new Color(252,194,1));
        g2.fillOval(x0+radius-radius/3, y0+radius-radius/3, petalWidth, petalHeight);

        g2.setColor(Color.yellow);
        g2.fillOval(x0, y0+radius, petalWidth, petalHeight);

        g2.setColor(new Color(252,194,1));
        g2.fillOval(x0-radius+radius/3, y0+radius-radius/3, petalWidth, petalHeight);
    }

}
