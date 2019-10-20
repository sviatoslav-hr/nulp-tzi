package ua.nulp.view;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainFrame extends JFrame {
    private HeaderPanel headerPanel;
    private MainPanel mainPanel;

    public MainFrame() {
        super("Text analytics by Sviatoslav Khrystyna");
        init();
        setHeaderPanel();
        setMainPanel();
        pack();
    }

    private void init() {
        setLocation(50, 0);
        setVisible(true);
        setResizable(false);
        setTheme();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setTheme() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setHeaderPanel() {
        this.headerPanel = new HeaderPanel();
        addToPanel(BorderLayout.NORTH, headerPanel);
    }

    private void setMainPanel() {
        mainPanel = new MainPanel();
        addToPanel(BorderLayout.CENTER, mainPanel);
    }

    public void setDiagramPanel(Map<String, Integer> data, int limit) {
        data = data.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().replace(" ", "_"),
                        Map.Entry::getValue));
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
        DiagramPanel diagramPanel = new DiagramPanel(getWidth() - 20, 500, charGroups, charGroupsNumbers);
        mainPanel.setBottomComponent(diagramPanel);
        pack();
    }

    public void setXyDiagramPanel(Map<String, List<Integer>> data, int limit, String... seriesNames) {
        DiagramPanel diagramPanel = new DiagramPanel(getWidth() - 20, 500, data, limit, seriesNames);
        mainPanel.setBottomComponent(diagramPanel);
        pack();
    }

    private void addToPanel(String name, Component component) {
        getContentPane().add(name, component);
    }

    public void refresh() {
        refreshMainPanel();
        headerPanel.getTextArea().setText("");
        pack();
    }

    private void refreshMainPanel() {
        mainPanel.removeTopComponent();
        mainPanel.removeCenterComponent();
        mainPanel.removeBottomComponent();
    }

    public String getInputText() {
        return headerPanel.getTextArea().getText();
    }

    public HeaderPanel getHeaderPanel() {
        return headerPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }
}
