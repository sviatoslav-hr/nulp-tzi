package ua.nulp.view.cipher;

import javax.swing.*;
import java.awt.*;

public class RSACipherPanel extends JPanel implements StatisticalCipherPanel {
    private JTextField pKeyField;
    private JTextField qKeyField;
    private JButton decodeButton;
    private JButton encodeButton;
    private JButton statisticsButton;

    public RSACipherPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setKeyFields();
        setDecodeButton();
        setButton();
        setStatisticsButton();
    }

    @Override
    public String getKey() {
        return pKeyField.getText() + " " + qKeyField.getText();
    }

    private void setKeyFields() {
        this.pKeyField = new JTextField(8);
        pKeyField.setText("37");
        add(new JLabel("p"));
        add(pKeyField);
        this.qKeyField = new JTextField(8);
        qKeyField.setText("83");
        add(new JLabel("q"));
        add(qKeyField);
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
