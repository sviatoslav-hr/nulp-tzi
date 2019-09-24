package ua.nulp.view;

import javax.swing.*;
import java.awt.*;

public class VigenerePanel extends JPanel {
    private JTextField keyField;
    private JButton decodeButton;
    private JButton encodeButton;

    public VigenerePanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setKeyField();
        setDecodeButton();
        setButton();
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
