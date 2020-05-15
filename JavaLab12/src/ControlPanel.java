import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    final String defaultText = "NewComponent";
    JTextField componentClass;
    JButton createComponent;
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
                frame.designPanel.addComponent((Component)userObject);
            }
        });
        add(componentClass);
        add(createComponent);
    }

}
