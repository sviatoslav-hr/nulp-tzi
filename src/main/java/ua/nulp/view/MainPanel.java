package ua.nulp.view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainPanel extends JPanel {
    private JComponent topComponent;
    private JComponent centerComponent;
    private JComponent bottomComponent;

    MainPanel() {
        setLayout(new BorderLayout());
    }

    public void setLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(new CompoundBorder(label.getBorder(),
                new EmptyBorder(10, 10, 10, 10)));
        setCenterComponent(label);
    }

    public void setTopComponent(JComponent component) {
        this.topComponent = component;
        add(topComponent, BorderLayout.NORTH);
    }

    public void setCenterComponent(JComponent centerComponent) {
        this.centerComponent = centerComponent;
        add(centerComponent, BorderLayout.CENTER);
    }

    public void setResultArea(String text) {
        removeBottomComponent();
        JTextArea textArea = new JTextArea(16, 50);
        textArea.setFont(textArea.getFont().deriveFont(18f));
        textArea.setText(text);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        setBottomComponent(new JScrollPane(textArea));
    }

    public void setBottomComponent(JComponent component) {
        this.bottomComponent = component;
        add(bottomComponent, BorderLayout.SOUTH);
    }

    public void removeTopComponent() {
        if (this.topComponent != null) {
            remove(topComponent);
            this.topComponent = null;
        }
    }

    public void removeCenterComponent() {
        if (centerComponent != null) {
            remove(centerComponent);
            this.centerComponent = null;
        }
    }

    public void removeBottomComponent() {
        if (bottomComponent != null) {
            remove(bottomComponent);
            this.bottomComponent = null;
        }
    }
}
