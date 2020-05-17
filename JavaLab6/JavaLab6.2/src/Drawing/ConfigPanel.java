/**
 * Author: Calin Irina, I2E2
 */
package Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel typeLabel;
    JLabel strokeLabel;
    JLabel vertexIndex;
    JTextField vertexTarget;
    JLabel vertexIndex2;
    JTextField vertexTarget2;
    JButton addEdge;

    JButton eraserButton;
    JButton formatterButton;
    JSpinner shapesStroke;
    boolean erasing = false;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the spinner
        typeLabel = new JLabel("GRAPH DRAWING TOOL -----");

        //create the colorCombo, containing the values: Random and Black

        strokeLabel = new JLabel("Eraser size:");
        shapesStroke = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
        shapesStroke.setValue(30); //default stroke size

        eraserButton = new JButton("Eraser: off");
        eraserButton.setBackground(new Color(127, 200, 167));

        formatterButton = new JButton("Format graph");
        formatterButton.setBackground(new Color(92, 200, 104));

        eraserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (erasing) {
                    eraserButton.setText("Eraser: off");
                    erasing = false;
                } else {
                    eraserButton.setText("Eraser: on");
                    erasing = true;
                }
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });

        vertexIndex = new JLabel("Add an edge between: ");
        vertexTarget = new JTextField("0");
        vertexTarget.setColumns(3);
        vertexIndex2 = new JLabel(" and ");
        vertexTarget2 = new JTextField("1");
        vertexTarget2.setColumns(3);

        addEdge = new JButton("Add edge");
        addEdge.setBackground(new Color(127, 200, 167));


        addEdge.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int vertexIndex = Integer.parseInt(vertexTarget.getText());
                int vertexIndex2 = Integer.parseInt(vertexTarget2.getText());

                int x0 = frame.canvas.vertices.get(vertexIndex).getX();
                int y0 = frame.canvas.vertices.get(vertexIndex).getY();
                int x1 = frame.canvas.vertices.get(vertexIndex2).getX();
                int y1 = frame.canvas.vertices.get(vertexIndex2).getY();
                frame.getCanvas().DrawEdge(x0, y0, x1, y1, vertexIndex, vertexIndex2);
                frame.getCanvas().repaint();
            }
        });

        formatterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Graph graph = new Graph(frame.getCanvas().getEdges(), frame.getCanvas().getVertices());
                graph.Format(frame.getCanvas().graphics);
                frame.getCanvas().clear();
                for (Vertex v : graph.vertices) {
                    frame.getCanvas().graphics.setColor(new Color(92, 200, 104));
                    frame.getCanvas().graphics.fill(v);
                    frame.getCanvas().graphics.setColor(Color.white);
                    frame.getCanvas().graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                    frame.getCanvas().graphics.drawString(Integer.toString(v.index),v.x-5,v.y+5);
                }
                for(Edge edge : graph.edges) {
                    frame.getCanvas().graphics.setColor(new Color(92, 200, 104));
                    //frame.getCanvas().graphics.drawLine(graph.vertices.get(edge.v1).x + 10,
                            //graph.vertices.get(edge.v1).y + 10, graph.vertices.get(edge.v2).x - 10, graph.vertices.get(edge.v1).y - 10);
                    //edge.drawMe(graph.vertices.get(edge.v2).x, graph.vertices.get(edge.v2).y, graph.vertices.get(edge.v1).x,
                            //graph.vertices.get(edge.v1).y, frame.getCanvas().graphics);
                    graph.drawEdgeForIndexes(edge.v1,edge.v2,frame.getCanvas().graphics);
                }
                frame.getCanvas().repaint();
            }
        });


        add(typeLabel);
        add(vertexIndex);
        add(vertexTarget);
        add(vertexIndex2);
        add(vertexTarget2);
        add(addEdge);
        add(formatterButton);
    }

    public MainFrame getFrame() {
        return frame;
    }


}
