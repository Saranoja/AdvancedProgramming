/**
 * Author: Calin Irina, I2E2
 */
package Drawing;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel; // we’re drawing regular polygons
    JLabel strokeLabel;
    JLabel colorLabel;
    JSpinner shapesStroke;
    JSpinner sidesField; // number of sides
    JComboBox colorCombo; // the color of the shape

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        //create the label and the spinner
        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(3, 3, 100, 1));
        sidesField.setValue(3); //default number of sides

        //create the colorCombo, containing the values: Random and Black

        String[] colors = {"Black", "Random"};
        colorLabel = new JLabel("Choose shape colour:");
        colorCombo = new JComboBox(colors);

        strokeLabel = new JLabel("Stroke size:");
        shapesStroke = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
        shapesStroke.setValue(30); //default stroke size

        add(sidesLabel); //JPanel uses FlowLayout by default
        add(sidesField);
        add(colorLabel);
        add(colorCombo);
        add(strokeLabel);
        add(shapesStroke);
    }

    public MainFrame getFrame() {
        return frame;
    }

    public JLabel getSidesLabel() {
        return sidesLabel;
    }

    public void setSidesLabel(JLabel sidesLabel) {
        this.sidesLabel = sidesLabel;
    }

    public JSpinner getSidesField() {
        return sidesField;
    }

    public void setSidesField(JSpinner sidesField) {
        this.sidesField = sidesField;
    }

    public JComboBox getColorCombo() {
        return colorCombo;
    }

    public void setColorCombo(JComboBox colorCombo) {
        this.colorCombo = colorCombo;
    }
}
