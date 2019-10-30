package ua.nulp.view.cipher;

import javax.swing.*;
import java.awt.*;

public class CaesarPanel extends JPanel {
    private JButton decodeButton;
    private JButton encodeButton;
    private JTextField shiftField;

    public CaesarPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setShiftField();
        setDecodeButton();
        setEncodeButton();
    }

    private void setShiftField() {
        this.shiftField = new JTextField(5);
        shiftField.setText("3");
        JButton increaseButton = new JButton("+");
        JButton decreaseButton = new JButton("-");
        add(decreaseButton);
        add(shiftField);
        add(increaseButton);
        increaseButton.addActionListener(e ->
                shiftField.setText(String.valueOf(getShift() + 1)));
        decreaseButton.addActionListener(e ->
                shiftField.setText(String.valueOf(getShift() - 1)));
    }

    public int getShift() {
        int caesarShift = 0;
        try {
            caesarShift = Integer.parseInt(shiftField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            shiftField.setText(String.valueOf(caesarShift));
        }
        return caesarShift;
    }

    private void setDecodeButton() {
        this.decodeButton = new JButton("Decode");
        add(decodeButton);
    }

    public JButton getDecodeButton() {
        return this.decodeButton;
    }

    private void setEncodeButton() {
        this.encodeButton = new JButton("Encode");
        add(encodeButton);
    }

    public JButton getEncodeButton() {
        return this.encodeButton;
    }
}
