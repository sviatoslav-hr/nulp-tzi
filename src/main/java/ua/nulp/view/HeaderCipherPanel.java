package ua.nulp.view;

import ua.nulp.enums.CipherType;

import javax.swing.*;
import java.util.Arrays;

public class HeaderCipherPanel extends JPanel {
    private JComboBox<String> comboBox;

    HeaderCipherPanel() {
        setComboBox();
    }


    public JComboBox getComboBox() {
        return comboBox;
    }

    private void setComboBox() {
        this.comboBox = new JComboBox<>();
        Arrays.stream(CipherType.values())
                .forEach(s -> this.comboBox.addItem(s.getName()));
        add(comboBox);
        comboBox.setSelectedItem(CipherType.NONE.getName());
    }
}
