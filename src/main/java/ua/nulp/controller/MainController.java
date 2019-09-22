package ua.nulp.controller;

import ua.nulp.enums.CipherType;
import ua.nulp.service.interfaces.TextAnalysingService;
import ua.nulp.view.CaesarPanel;
import ua.nulp.view.MainFrame;

import javax.swing.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class MainController {
    private MainFrame mainFrame;
    private TextAnalysingService textAnalysingService;

    public MainController(MainFrame mainFrame, TextAnalysingService textAnalysingService) {
        this.mainFrame = mainFrame;
        this.textAnalysingService = textAnalysingService;
        setUpListeners();
    }

    private void setUpListeners() {
        mainFrame.getHeaderPanel().getAnalysePanel().getAnalyseButton()
                .addActionListener(event -> runAnalyse());
        mainFrame.getHeaderPanel().getClearButton()
                .addActionListener(e -> clear());
        mainFrame.getHeaderPanel().getAlphabetPanel().getAlphabetButton()
                .addActionListener(e -> countAlphabetEntries());
        mainFrame.getHeaderPanel().getCipherPanel().getComboBox()
                .addActionListener(e -> {
                    JComboBox source = (JComboBox) (e.getSource());
                    String selectedItem = (String) source.getSelectedItem();
                    if (Objects.equals(selectedItem, CipherType.CAESAR.getName())) {
                        selectCaesarCipher();
                    } else {
                        unselectCipher();
                    }
                });
    }

    private void clear() {
        mainFrame.refresh();
    }

    private void runAnalyse() {
        mainFrame.removeDiagramPanel();
        String text = mainFrame.getHeaderPanel().getTextArea().getText();
        if (text.length() == 0) {
            return;
        }
        int length = mainFrame.getHeaderPanel().getAnalysePanel().getCharsGroupLength();
        int minEntries = mainFrame.getHeaderPanel().getAnalysePanel().getMinEntriesNumber();
        setAlphabet(text);
        Map<String, Integer> data = textAnalysingService.countCharGroupEntries(text, 2, length);
        mainFrame.setDiagramPanel(data, minEntries);
    }

    private void setAlphabet(String text) {
        char[] alphabet = textAnalysingService.getAlphabetOf(text).toCharArray();
        mainFrame.getCenterPanel().setAlphabetLabel("Alphabet: " + Arrays.toString(alphabet));
    }


    private void countAlphabetEntries() {
        mainFrame.removeDiagramPanel();
        String text = mainFrame.getInputText();
        if (text.length() == 0) {
            return;
        }
        setAlphabet(text);
        Map<String, Integer> data = textAnalysingService.countAlphabetEntries(text);
        mainFrame.setDiagramPanel(data, data.size());
    }

    private void unselectCipher() {
        mainFrame.getCenterPanel().clearComponent();
        mainFrame.getCenterPanel().removeResultArea();
        mainFrame.pack();
    }

    private void selectCaesarCipher() {
        CaesarPanel caesarPanel = new CaesarPanel();
        caesarPanel.getDecodeCaesarShiftButton().addActionListener(e -> solveCesarCipher(caesarPanel, true));
        caesarPanel.getEncodeCaesarButton().addActionListener(e -> solveCesarCipher(caesarPanel, false));
        mainFrame.getCenterPanel().setComponent(caesarPanel);
        mainFrame.pack();
    }


    private void solveCesarCipher(CaesarPanel caesarPanel, boolean isEncoded) {
        mainFrame.removeDiagramPanel();
        mainFrame.getCenterPanel().removeAlphabet();
        int caesarShift = caesarPanel.getCaesarShift();
        String decodedText = isEncoded ? textAnalysingService.decodeCesarCipher(mainFrame.getInputText(), caesarShift)
                : textAnalysingService.encodeCesarCipher(mainFrame.getInputText(), caesarShift);
        mainFrame.getCenterPanel().setResultArea(decodedText);
        mainFrame.pack();
    }
}
