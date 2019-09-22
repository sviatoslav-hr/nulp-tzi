package ua.nulp.view;

import javax.swing.*;
import java.awt.*;

public class CaesarPanel extends JPanel {
    private JButton decodeCaesarShiftButton;
    private JButton encodeCaesarButton;
    private JTextField caesarShiftField;

    public CaesarPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setCaesarShiftField();
        setDecodeCaesarButton();
        setEncodeCaesarButton();
    }

    private void setCaesarShiftField() {
        this.caesarShiftField = new JTextField(5);
        caesarShiftField.setText("3");
        JButton increaseButton = new JButton("+");
        JButton decreaseButton = new JButton("-");
        add(decreaseButton);
        add(caesarShiftField);
        add(increaseButton);
        increaseButton.addActionListener(e ->
                caesarShiftField.setText(String.valueOf(getCaesarShift() + 1)));
        decreaseButton.addActionListener(e ->
                caesarShiftField.setText(String.valueOf(getCaesarShift() - 1)));
    }

    public int getCaesarShift() {
        int caesarShift = 0;
        try {
            caesarShift = Integer.parseInt(caesarShiftField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            caesarShiftField.setText(String.valueOf(caesarShift));
        }
        return caesarShift;
    }

    private void setDecodeCaesarButton() {
        this.decodeCaesarShiftButton = new JButton("Decode");
        add(decodeCaesarShiftButton);
    }

    public JButton getDecodeCaesarShiftButton() {
        return this.decodeCaesarShiftButton;
    }

    private void setEncodeCaesarButton() {
        this.encodeCaesarButton = new JButton("Encode");
        add(encodeCaesarButton);
    }

    public JButton getEncodeCaesarButton() {
        return this.encodeCaesarButton;
    }
}
