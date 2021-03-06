package ua.nulp.view.cipher;

import javax.swing.*;
import java.awt.*;

public class HillCipherPanel extends JPanel implements StatisticalCipherPanel {
    private JTextField keyField;
    private JButton decodeButton;
    private JButton encodeButton;
    private JButton statisticsButton;

    public HillCipherPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setKeyField();
        setDecodeButton();
        setButton();
        setStatisticsButton();
    }

    public JTextField getKeyField() {
        return keyField;
    }

    @Override
    public String getKey() {
        return keyField.getText();
    }

    private void setKeyField() {
        add(new JLabel("key"));
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

    public JButton getStatisticsButton() {
        return statisticsButton;
    }

    private void setStatisticsButton() {
        this.statisticsButton = new JButton("Statistics");
        add(statisticsButton);
    }
}
