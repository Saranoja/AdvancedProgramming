import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    final String defaultText = "NewComponent";
    JTextField componentClass;
    JButton createComponent;
    JButton saveButton;
    JButton loadButton;
    String chosenClass;
    Class userClass;
    Object userObject;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        componentClass = new JTextField("Enter class", 15);
        createComponent = new JButton("Create");
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");

        createComponent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chosenClass = componentClass.getText();
                try {
                    userClass = Class.forName(chosenClass);
                    System.out.println("User choice: " + chosenClass);
                    System.out.println("Class: " + userClass);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                try {
                    userObject = userClass.getConstructor().newInstance();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                frame.designPanel.addComponent((Component) userObject);
            }
        });
        loadButton.addActionListener(this::load);
        saveButton.addActionListener(this::save);

        add(componentClass);
        add(createComponent);
        add(loadButton);
        add(saveButton);
    }

    private void load(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select an xml document");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML documents", "xml");
        fileChooser.addChoosableFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getPath());

            try {
                XMLDecoder decoder =
                        new XMLDecoder(new BufferedInputStream(
                                new FileInputStream(file)));

                DesignPanel designPanel = (DesignPanel) decoder.readObject();
                decoder.close();
                this.frame.updateDesignPanel(designPanel);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void save(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Choose a directory to save your file");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile() + "\\panel.xml");
            try {
                XMLEncoder encoder = new XMLEncoder(
                        new BufferedOutputStream(
                                new FileOutputStream(file)));

                encoder.writeObject(this.frame.getDesignPanel());
                encoder.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
