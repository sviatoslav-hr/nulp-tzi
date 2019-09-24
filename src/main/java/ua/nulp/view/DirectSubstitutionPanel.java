package ua.nulp.view;

import javax.swing.*;
import java.awt.*;

public class DirectSubstitutionPanel extends JPanel {
    private JButton randomButton;
    private JTextField keyField;
    private JButton decodeButton;
    private JButton encodeButton;

    public DirectSubstitutionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setRandomButton();
        setKeyField();
        setDecodeButton();
        setButton();
    }

    public JButton getRandomButton() {
        return randomButton;
    }

    private void setRandomButton() {
        this.randomButton = new JButton("Random");
        add(randomButton);
    }

    public JTextField getKeyField() {
        return keyField;
    }

    public String getKey() {
        return keyField.getText();
    }

    private void setKeyField() {
        this.keyField = new JTextField(32);
        add(keyField);
    }

    private void setDecodeButton() {
        this.decodeButton = new JButton("Decode");
        add(decodeButton);
    }

    public JButton getDecodeButton() {
        return this.decodeButton;
    }

    private void setButton() {
        this.encodeButton = new JButton("Encode");
        add(encodeButton);
    }

    public JButton getEncodeButton() {
        return this.encodeButton;
    }
}
