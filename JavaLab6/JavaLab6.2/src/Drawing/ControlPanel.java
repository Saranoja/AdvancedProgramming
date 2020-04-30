/**
 * Author: Calin Irina, I2E2
 */
package Drawing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {

    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    JButton eraserButton = new JButton("Eraser: off");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        //configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
    }

    private void save(ActionEvent e) {
        /* try {
            ImageIO.write(frame.canvas.image, "PNG", new File("c:/users/irina/desktop/output.png"));
        } catch (IOException ex) {
            System.err.println(ex);
        } */

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a directory");
        fileChooser.setCurrentDirectory(new File("."));
        //only directories
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.showOpenDialog(this);

        JOptionPane optionPane = new JOptionPane();
        String fileName = JOptionPane.showInputDialog("Enter the name of the file:");
        File location = new File(fileChooser.getSelectedFile().getAbsolutePath() + "\\" + fileName + ".png");
        try {
            ImageIO.write(frame.canvas.image, "PNG", location);
        } catch (IOException exceptionx) {
            System.err.println(exceptionx);
        }
    }

    private void load(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a png file:");
        fileChooser.setCurrentDirectory(new File("."));
        //only files
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(this);
        try {
            frame.canvas.setImage(ImageIO.read(new File(fileChooser.getSelectedFile().getAbsolutePath())));
            frame.canvas.repaint();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void reset(ActionEvent ae) {
        System.out.println("Canvas reset");
        frame.canvas.clear();
        frame.getCanvas().copyX = -1000;
        frame.getCanvas().copyY = -1000;
        frame.getCanvas().index = 0;
        frame.getCanvas().vertices.clear();
        frame.getCanvas().edges.clear();
    }

    private void exit(ActionEvent e) {
        System.exit(0);
    }

}
