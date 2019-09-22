package ua.nulp.view;

import javax.swing.*;
import java.awt.*;

public class HeaderAlphabetPanel extends JPanel {
    private JCheckBox sortingCheckBox;
    private JButton alphabetButton;

    HeaderAlphabetPanel() {
        super(new FlowLayout(FlowLayout.LEFT));
        setAlphabetButton();
        setSortingCheckBox();
    }

    private void setAlphabetButton() {
        alphabetButton = new JButton("Analyse Alphabet");
        add(BorderLayout.WEST, alphabetButton);
    }

    private void setSortingCheckBox() {
        sortingCheckBox = new JCheckBox("Sorted");
        add(BorderLayout.EAST, sortingCheckBox);
    }

    public JButton getAlphabetButton() {
        return alphabetButton;
    }

    public JCheckBox getSortingCheckBox() {
        return sortingCheckBox;
    }
}
