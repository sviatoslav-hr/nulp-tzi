package ua.nulp.view.cipher;

import javax.swing.*;
import java.awt.*;

public class FeistelCipherPanel extends JPanel implements StatisticalCipherPanel {
    private JTextField nKeyField;
    private JTextField kKeyField;
    private JButton decodeButton;
    private JButton encodeButton;
    private JButton statisticsButton;

    public FeistelCipherPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setKeyFields();
        setDecodeButton();
        setButton();
        setStatisticsButton();
    }

    @Override
    public String getKey() {
        return nKeyField.getText() + " " + kKeyField.getText();
    }

    private void setKeyFields() {
        this.nKeyField = new JTextField(8);
        nKeyField.setText("5");
        add(new JLabel("n"));
        add(nKeyField);
        this.kKeyField = new JTextField(8);
        kKeyField.setText("11");
        add(new JLabel("k"));
        add(kKeyField);
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
