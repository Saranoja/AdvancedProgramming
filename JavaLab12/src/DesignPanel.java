/**
 * @author: Calin Irina, I2E2
 */

import javax.swing.*;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DesignPanel extends JPanel {
    MainFrame frame;
    final static int W = 800, H = 600;
    private int defX = 50;
    private int defY = 50;
    private ArrayList<Component> components;
    int i = 0;

    public DesignPanel() {
    }

    public DesignPanel(MainFrame frame) {
        components = new ArrayList<>();
        this.frame = frame;
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        setBackground(new Color(180, 209, 250));
    }

    public void addComponent(Component component) {
        //component.setSize(new Dimension(50, 20));
        components.add(component);
        add(component);
        JButton newComponent;
        JLabel newComponent2;
        JCheckBox newComponent3;
        JTextField newComponent4;

        Class componentClass = component.getClass();
        int defW = 80;
        int defH = 50;
        switch (componentClass.toString()) {
            case "class javax.swing.JButton":
                newComponent = (JButton) component;
                newComponent.setText("Default");
                newComponent.setBounds(defX, defY, defW, defH);
                add(newComponent);
                this.frame.getDesignPanel().addFocusListenerToComponent(newComponent);
                break;
            case "class javax.swing.JLabel":
                newComponent2 = (JLabel) component;
                newComponent2.setText("Label");
                newComponent2.setBounds(defX, defY, defW, defH);
                add(newComponent2);
                this.frame.getDesignPanel().addFocusListenerToComponent(newComponent2);
                break;
            case "class javax.swing.JCheckBox":
                newComponent3 = (JCheckBox) component;
                newComponent3.setText("CheckBox");
                newComponent3.setBounds(defX, defY, defW, defH);
                add(newComponent3);
                this.frame.getDesignPanel().addFocusListenerToComponent(newComponent3);
                break;
            case "class javax.swing.JTextField":
                newComponent4 = (JTextField) component;
                newComponent4.setText("TextField");
                newComponent4.setBounds(defX, defY, defW, defH);
                add(newComponent4);
                this.frame.getDesignPanel().addFocusListenerToComponent(newComponent4);
                break;
            default:
                component.setBounds(defX, defY, defW, defH);
                this.frame.getDesignPanel().addFocusListenerToComponent(component);
                break;
        }

        defX += 100;
        if (defX > 700) {
            defX = 50;
            defY += 100;
        }
        if (defY > 500) {
            defY = 50;
            defX = 50;
        }
        component.setVisible(true);
        add(component);
    }

    private void updateAll() {
        for (Component component : components) {
            component.setVisible(true);
            add(component);
        }
    }

    public void addFocusListenerToComponent(Component component) {
        component.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                BeanInfo info = null;
                Class<?> componentClass = component.getClass();
                try {
                    info = Introspector.getBeanInfo(componentClass);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                String type;
                int xRow = 0, yRow = 0, widthRow = 0, heightRow = 0;
                DefaultTableModel model = (DefaultTableModel) frame.getPropertiesPanel().getPropertiesTable().getModel();
                assert info != null;
                for (PropertyDescriptor propertyDescriptor : info.getPropertyDescriptors()) {
                    switch (propertyDescriptor.getName()) {
                        case "width":
                            widthRow = i;
                            break;
                        case "height":
                            heightRow = i;
                            break;
                        case "x":
                            xRow = i;
                            break;
                        case "y":
                            yRow = i;
                            break;
                        default:
                            break;
                    }
                    model.setValueAt(String.valueOf(propertyDescriptor.getPropertyType()), i, 0);
                    model.setValueAt(String.valueOf(propertyDescriptor.getName()), i, 1);
                    if (propertyDescriptor.getPropertyType() == null) {
                        continue;
                    } else {
                        type = propertyDescriptor.getPropertyType().getTypeName();
                    }
                    if (type.equals("int") || type.equals("java.lang.String"))
                        try {
                            model.setValueAt(propertyDescriptor.getReadMethod().invoke(component), i, 2);
                        } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                    ++i;
                }
                int finalXRow = xRow;
                int finalYRow = yRow;
                int finalHeightRow = heightRow;
                int finalWidthRow = widthRow;
                frame.getPropertiesPanel().getPropertiesTable().addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        updateValueOn(0, String.valueOf(model.getValueAt(finalXRow, 2)),
                                String.valueOf(model.getValueAt(finalYRow, 2)), String.valueOf(model.getValueAt(finalWidthRow, 2)),
                                String.valueOf(model.getValueAt(finalHeightRow, 2)));
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        // nothing
                    }
                });
            }

            @Override
            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
    }

    public void updateValueOn(int componentIndex, String newX, String newY, String newWidth, String newHeight) {
        DefaultTableModel model = (DefaultTableModel) frame.getPropertiesPanel().getPropertiesTable().getModel();
        for (int j = 0; j < i; ++j) {
            components.get(componentIndex).setLocation(Integer.parseInt(newX), Integer.parseInt(newY));
            components.get(componentIndex).setSize(Integer.parseInt(newWidth), Integer.parseInt(newHeight));
        }
        System.out.println("Updated value");
        this.repaint();
        this.updateAll();
        this.repaint();
    }
}
