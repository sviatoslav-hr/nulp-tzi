package ua.nulp.view;

import javax.swing.*;
import java.awt.*;

public class HeaderAnalysePanel extends JPanel {
    private JButton analyseButton;
    private JTextField charGroupLengthField;
    private JTextField minEntriesField;

    HeaderAnalysePanel() {
        super(new FlowLayout(FlowLayout.LEFT));
        setAnalyseButton();
        setCharsGroupLengthField();
        setMinAmountField();
    }

    private void setAnalyseButton() {
        analyseButton = new JButton("Analyse");
        add(analyseButton);
    }

    public JButton getAnalyseButton() {
        return analyseButton;
    }

    private void setCharsGroupLengthField() {
        charGroupLengthField = new JTextField();
        charGroupLengthField.setText("2");
        charGroupLengthField.setColumns(5);
        add(charGroupLengthField);
    }

    private void setMinAmountField() {
        minEntriesField = new JTextField();
        minEntriesField.setText("15");
        minEntriesField.setColumns(5);
        add(minEntriesField);
    }

    public int getCharsGroupLength() {
        int length = 2;
        try {
            length = Integer.parseInt(charGroupLengthField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            charGroupLengthField.setText(String.valueOf(length));
        }
        return length;
    }

    public int getMinEntriesNumber() {
        int minEntries = 10;
        try {
            minEntries = Integer.parseInt(minEntriesField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            charGroupLengthField.setText(String.valueOf(minEntries));
        }
        return minEntries;
    }
}
