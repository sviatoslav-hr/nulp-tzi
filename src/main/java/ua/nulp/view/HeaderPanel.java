package ua.nulp.view;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private JTextArea textArea;
    private HeaderAnalysePanel analysePanel;
    private HeaderAlphabetPanel alphabetPanel;
    private HeaderCipherPanel cipherPanel;
    private JButton clearButton;

    HeaderPanel() {
        setLayout(new GridBagLayout());
        setTextArea();
        setAnalysePanel();
        setAlphabetPanel();
        setCipherPanel();
        setClearButton();
    }

    private void setTextArea() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 3;
        textArea = new JTextArea(5, 48);
        textArea.setFocusable(true);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea), constraints);
    }

    private void setAnalysePanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        analysePanel = new HeaderAnalysePanel();
        add(analysePanel, constraints);
    }

    private void setAlphabetPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        alphabetPanel = new HeaderAlphabetPanel();
        add(alphabetPanel, constraints);
    }

    private void setCipherPanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 2;
        this.cipherPanel = new HeaderCipherPanel();
        add(cipherPanel, constraints);
    }

    private void setClearButton() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        clearButton = new JButton("Clear");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(clearButton);
        add(panel, constraints);
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public HeaderAnalysePanel getAnalysePanel() {
        return analysePanel;
    }

    public HeaderAlphabetPanel getAlphabetPanel() {
        return alphabetPanel;
    }

    public HeaderCipherPanel getCipherPanel() {
        return cipherPanel;
    }
}
