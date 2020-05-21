import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;

public class DesignPanel extends JPanel {
    MainFrame frame;
    final static int W = 800, H = 600;
    private int defX = 50;
    private int defY = 50;
    private int defW = 80;
    private int defH = 50;
    private ArrayList<Component> components;

    public DesignPanel() {
    }

    public DesignPanel(MainFrame frame) {
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
        //components.add(component);
        add(component);
        JButton newComponent;
        JLabel newComponent2;
        JCheckBox newComponent3;
        JTextField newComponent4;

        Class componentClass = component.getClass();
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
                int i = 0;
                DefaultTableModel model = (DefaultTableModel) frame.getPropertiesPanel().getPropertiesTable().getModel();
                for (PropertyDescriptor propertyDescriptor : info.getPropertyDescriptors()) {
                    model.setValueAt(String.valueOf(propertyDescriptor.getPropertyType()), i, 0);
                    model.setValueAt(String.valueOf(propertyDescriptor.getName()), i, 1);
                    ++i;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
    }
}
