package ua.nulp.view;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainFrame extends JFrame {
    private HeaderPanel headerPanel;
    private DiagramPanel diagramPanel;
    private CenterPanel centerPanel;

    public MainFrame() {
        super("Text analytics");
        init();
        setHeaderPanel();
        setCenterPanel();
        pack();
    }

    private void init() {
        setLocation(50, 0);
        setVisible(true);
        setResizable(false);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setHeaderPanel() {
        this.headerPanel = new HeaderPanel();
        addToPanel(BorderLayout.NORTH, headerPanel);
    }

    private void setCenterPanel() {
        centerPanel = new CenterPanel();
        addToPanel(BorderLayout.CENTER, centerPanel);
    }

    public void setDiagramPanel(Map<String, Integer> data, int limit) {
        List<String> charGroups = data.keySet()
                .stream()
                .sorted(Comparator.comparingInt(data::get).reversed())
                .limit(limit)
                .collect(Collectors.toList());
        boolean alphabetSortingSelected = headerPanel.getAlphabetPanel()
                .getSortingCheckBox().isSelected();
        if (alphabetSortingSelected) {
            charGroups = charGroups.stream()
                    .sorted(String::compareTo)
                    .collect(Collectors.toList());
        }
        List<Integer> charGroupsNumbers = charGroups.stream()
                .map(data::get)
                .collect(Collectors.toList());
        this.diagramPanel = new DiagramPanel(getWidth() - 20, 500, charGroups, charGroupsNumbers);
        addToPanel(BorderLayout.SOUTH, this.diagramPanel);
        pack();
    }

    public void removeDiagramPanel() {
        if (this.diagramPanel != null) {
            remove(this.diagramPanel);
            this.diagramPanel = null;
            pack();
        }
    }

    private void addToPanel(String name, Component component) {
        getContentPane().add(name, component);
    }

    public void refresh() {
        removeDiagramPanel();
        refreshCenterPanel();
        headerPanel.getTextArea().setText("");
        pack();
    }

    private void refreshCenterPanel() {
        centerPanel.removeAlphabetLabel();
        centerPanel.removeResultArea();
    }

    public String getInputText() {
        return headerPanel.getTextArea().getText();
    }

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }

    public DiagramPanel getDiagramPanel() {
        return diagramPanel;
    }

    public CenterPanel getCenterPanel() {
        return centerPanel;
    }
}
