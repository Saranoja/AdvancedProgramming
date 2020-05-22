/**
 * @author: Calin Irina, I2E2
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;
    PropertiesPanel propertiesPanel;
    final int W = 800, H = 600;

    public MainFrame() {
        super("JavaLab12");
        init();
    }

    public void init() {
        rootPane.setBorder(BorderFactory.createTitledBorder("Canvas"));
        this.setSize(new Dimension(W, H));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        controlPanel = new ControlPanel(this);
        designPanel = new DesignPanel(this);
        propertiesPanel = new PropertiesPanel(this);
        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.CENTER);
        add(propertiesPanel, BorderLayout.SOUTH);
    }

    public void updateDesignPanel(DesignPanel designPanel) {
        remove(this.designPanel);
        this.designPanel = designPanel;
        add(designPanel, BorderLayout.CENTER);
        pack();
    }

    public DesignPanel getDesignPanel() {
        return designPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public PropertiesPanel getPropertiesPanel() {
        return propertiesPanel;
    }
}
