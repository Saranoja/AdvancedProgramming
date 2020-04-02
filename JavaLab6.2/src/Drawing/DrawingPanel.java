/**
 * Author: Calin Irina, I2E2
 */
package Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.event.MouseEvent.MOUSE_CLICKED;
import static java.awt.event.MouseEvent.MOUSE_PRESSED;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    int copyX = -1000;
    int copyY = -1000;
    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();
    int index = 0;

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
                //drawShape(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                copyX = e.getX();
                copyY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                /*if (copyX != -1000 && copyY != -1000)
                    if (!frame.getConfigPanel().erasing)
                        DrawEdge(e.getX(), e.getY());
                repaint();*/
            }
        });
    }

    public void DrawEdge(int x, int y, int x1, int y1, int index1, int index2) {
        Edge edge = new Edge(x, y, x1, y1, graphics);
        edge.setVertices(index1,index2);
        edges.add(edge);
        vertices.get(index1).increaseDegree();
        vertices.get(index2).increaseDegree();
        //System.out.println("Degree for vertex " + index1 + "= " + vertices.get(index1).degree);
        //System.out.println("Degree for vertex " + index2 + "= " + vertices.get(index2).degree);
    }

    private void drawShape(int x, int y) {
        Random rand = new Random();
        int radius = (Integer) (frame.getConfigPanel().shapesStroke.getValue());
        if (!frame.getConfigPanel().erasing) {
            graphics.setColor(new Color(255, 111, 91));
            Vertex v = new Vertex(x, y, index);
            graphics.fill(v);
            graphics.setColor(Color.white);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            graphics.drawString(Integer.toString(v.index),x-5,y+5);
            vertices.add(v);
            System.out.println("added vertex: " + v.index);
            index++;
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

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
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
