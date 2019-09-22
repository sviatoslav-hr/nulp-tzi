package ua.nulp.view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CenterPanel extends JPanel {
    private JLabel alphabetLabel;
    private JComponent component;
    private JComponent resultAreaPane;

    CenterPanel() {
        setLayout(new BorderLayout());
    }

    public void setAlphabetLabel(String text) {
        JLabel label = new JLabel(text);
        label.setBorder(new CompoundBorder(label.getBorder(),
                new EmptyBorder(10, 10, 10, 10)));
        this.alphabetLabel = label;
        add(alphabetLabel, BorderLayout.CENTER);
    }

    public JLabel getAlphabetLabel() {
        return alphabetLabel;
    }

    void removeAlphabetLabel() {
        if (this.alphabetLabel != null) {
            remove(alphabetLabel);
            this.alphabetLabel = null;
        }
    }

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent component) {
        this.component = component;
        add(component, BorderLayout.NORTH);
    }

    public void setResultArea(String text) {
        removeResultArea();
        JTextArea textArea = new JTextArea(16, 50);
        textArea.setText(text);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        this.resultAreaPane = new JScrollPane(textArea);
        add(resultAreaPane, BorderLayout.SOUTH);
    }

    public void clearComponent() {
        if (component != null) {
            remove(component);
            this.component = null;
        }
    }

    public void removeResultArea() {
        if (resultAreaPane != null) {
            remove(resultAreaPane);
            this.resultAreaPane = null;
        }
    }

    public void removeAlphabet() {
        if (alphabetLabel != null) {
            remove(alphabetLabel);
            this.alphabetLabel = null;
        }
    }
}
