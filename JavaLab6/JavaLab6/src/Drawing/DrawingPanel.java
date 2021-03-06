/**
 * Author: Calin Irina, I2E2
 */
package Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import static java.awt.event.MouseEvent.MOUSE_CLICKED;
import static java.awt.event.MouseEvent.MOUSE_PRESSED;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        //use setSize() if your component's parent has no layout manager
        //setPreferredSize() and its related setMinimumSize and setMaximumSize if it does. And it does - grid.
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases

            @Override
            public void mouseDragged(MouseEvent e) {
                if (frame.getConfigPanel().erasing)
                    drawShape(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (frame.getConfigPanel().erasing)
                    drawShape(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private void drawShape(int x, int y) {
        Random rand = new Random();
        String figureType = (String) (frame.getConfigPanel().figureTypes.getSelectedItem());
        int radius = (Integer) (frame.getConfigPanel().shapesStroke.getValue());
        if (!frame.getConfigPanel().erasing) {
            if (figureType == "Regular polygon") {
                frame.getConfigPanel().sidesField.setVisible(true);
                frame.getConfigPanel().sidesLabel.setVisible(true);
                frame.getConfigPanel().colorCombo.enable();
                int sides = (Integer) (frame.getConfigPanel().sidesField.getValue()); //get the value from UI (in Drawing.ConfigPanel)
                String temp = (String) (frame.getConfigPanel().colorCombo.getSelectedItem());
                Color color = new Color(0xFFFFFF);
                if (temp == "Black")
                    color = Color.black;
                else
                    color = new Color(rand.nextInt(0xFFFFFF)); //create a transparent random Color.
                graphics.setColor(color);
                graphics.fill(new RegularPolygon(x, y, radius, sides));
            } else {
                frame.getConfigPanel().sidesField.setVisible(false);
                frame.getConfigPanel().sidesLabel.setVisible(false);
                frame.getConfigPanel().colorCombo.disable();
                switch (figureType) {
                    case "Flower": {
                        Flower flower = new Flower(x, y, radius, graphics);
                        break;
                    }
                    case "Cloud": {
                        Cloud cloud = new Cloud(x, y, radius, graphics);
                        break;
                    }
                    case "Spectre": {
                        Spectre spectre = new Spectre(x, y, radius, graphics);
                        break;
                    }
                }
            }
        } else {
            graphics.setColor(Color.white);
            graphics.fill(new Eraser(x, y, radius));
            //graphics.clearRect(x - radius / 2, y - radius / 2, radius, radius);
        }
    }

    public void clear() {
        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        graphics.setPaint(Color.black);
        repaint();
    }

    @Override
    public void update(Graphics g) {
    } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
}
