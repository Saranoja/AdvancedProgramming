/**
 * @author: Calin Irina, I2E2
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PropertiesPanel extends JPanel {
    private final MainFrame frame;
    private final JTable propertiesTable;

    public PropertiesPanel(MainFrame frame) {
        this.frame = frame;
        this.propertiesTable = new JTable(new DefaultTableModel(new String[]{"Type", "Name", "Value"}, 100));
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(800, 400));
        JScrollPane scrollTable = new JScrollPane(propertiesTable);
        add(scrollTable, BorderLayout.CENTER);
    }

    public JTable getPropertiesTable() {
        return propertiesTable;
    }
}