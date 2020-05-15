import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;
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
        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.CENTER);
    }

}
