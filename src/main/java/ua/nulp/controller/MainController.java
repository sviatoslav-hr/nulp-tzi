package ua.nulp.controller;

import ua.nulp.enums.CipherType;
import ua.nulp.service.implementation.CaesarCipherService;
import ua.nulp.service.implementation.DirectSubstitutionCipherService;
import ua.nulp.service.implementation.VigenereCipherService;
import ua.nulp.service.interfaces.AlphabetService;
import ua.nulp.service.interfaces.CipherService;
import ua.nulp.service.interfaces.TextAnalysingService;
import ua.nulp.view.CaesarPanel;
import ua.nulp.view.DirectSubstitutionPanel;
import ua.nulp.view.MainFrame;
import ua.nulp.view.VigenerePanel;

import javax.swing.*;
import java.util.*;

public class MainController {
    private MainFrame mainFrame;
    private TextAnalysingService textAnalysingService;
    private CipherService cipherService;
    private AlphabetService alphabetService;

    public MainController(MainFrame mainFrame, TextAnalysingService textAnalysingService,
                          AlphabetService alphabetService) {
        this.mainFrame = mainFrame;
        this.textAnalysingService = textAnalysingService;
        this.alphabetService = alphabetService;
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
                    } else if (Objects.equals(selectedItem, CipherType.DIRECT_SUBSTITUTION.getName())) {
                        selectDirectSubstitutionCipher();
                    } else if (Objects.equals(selectedItem, CipherType.VIGENERE.getName())) {
                        selectVigenereCipher();
                    } else {
                        unselectCipher();
                    }
                });
    }

    private void clear() {
        mainFrame.getMainPanel().removeCenterComponent();
        mainFrame.getMainPanel().removeBottomComponent();
        mainFrame.getHeaderPanel().getTextArea().setText("");
        mainFrame.pack();
    }

    private void runAnalyse() {
        mainFrame.getMainPanel().removeCenterComponent();
        mainFrame.getMainPanel().removeBottomComponent();
        String text = mainFrame.getHeaderPanel().getTextArea().getText();
        if (text.length() == 0) {
            return;
        }
        int length = mainFrame.getHeaderPanel().getAnalysePanel().getCharsGroupLength();
        int limit = mainFrame.getHeaderPanel().getAnalysePanel().getMinEntriesNumber();
        setAlphabet(text);
        Map<String, Integer> data = textAnalysingService.countCharGroupEntries(text, 2, length);
        mainFrame.setDiagramPanel(data, limit);
    }

    private void setAlphabet(String text) {
        char[] alphabet = textAnalysingService.getAlphabetOf(text).toCharArray();
        mainFrame.getMainPanel().removeCenterComponent();
        mainFrame.getMainPanel().setLabel("Alphabet: " + Arrays.toString(alphabet));
    }


    private void countAlphabetEntries() {
        mainFrame.getMainPanel().removeCenterComponent();
        mainFrame.getMainPanel().removeBottomComponent();
        String text = mainFrame.getInputText();
        if (text.length() == 0) {
            return;
        }
        setAlphabet(text);
        Map<String, Integer> data = textAnalysingService.countAlphabetEntries(text);
        mainFrame.setDiagramPanel(data, data.size());
    }

    private void unselectCipher() {
        mainFrame.getMainPanel().removeTopComponent();
        mainFrame.pack();
    }

    private void selectCaesarCipher() {
        unselectCipher();
        setCipherService(new CaesarCipherService(alphabetService));
        CaesarPanel caesarPanel = new CaesarPanel();
        caesarPanel.getDecodeButton()
                .addActionListener(e -> solveCipher(caesarPanel.getShift(), true));
        caesarPanel.getEncodeButton()
                .addActionListener(e -> solveCipher(caesarPanel.getShift(), false));
        mainFrame.getMainPanel().setTopComponent(caesarPanel);
        mainFrame.pack();
    }

    private void selectDirectSubstitutionCipher() {
        unselectCipher();
        setCipherService(new DirectSubstitutionCipherService(alphabetService));
        DirectSubstitutionPanel panel = new DirectSubstitutionPanel();
        panel.getDecodeButton()
                .addActionListener(e -> solveCipher(panel.getKey(), true));
        panel.getEncodeButton()
                .addActionListener(e -> solveCipher(panel.getKey(), false));
        panel.getRandomButton()
                .addActionListener(e -> panel.getKeyField()
                        .setText(shuffleString(alphabetService.getAlphabet())));
        mainFrame.getMainPanel().setTopComponent(panel);
        mainFrame.pack();
    }

    private void selectVigenereCipher() {
        unselectCipher();
        setCipherService(new VigenereCipherService(alphabetService));
        VigenerePanel panel = new VigenerePanel();
        panel.getDecodeButton()
                .addActionListener(e -> solveCipher(panel.getKey(), true));
        panel.getEncodeButton()
                .addActionListener(e -> solveCipher(panel.getKey(), false));
        mainFrame.getMainPanel().setTopComponent(panel);
        mainFrame.pack();
    }


    private void solveCipher(Object key, boolean isEncoded) {
        mainFrame.getMainPanel().removeCenterComponent();
        mainFrame.getMainPanel().removeBottomComponent();
        String text = mainFrame.getInputText();
        String resultText = isEncoded ? cipherService.decode(text, key)
                : cipherService.encode(text, key);
        mainFrame.getMainPanel().setResultArea(resultText);
        mainFrame.pack();
    }

    private void setCipherService(CipherService cipherService) {
        this.cipherService = cipherService;
    }

    private String shuffleString(String input) {
        List<String> characters = new LinkedList<>(Arrays.asList(input.split("")));
        StringBuilder output = new StringBuilder(input.length());
        Random random = new Random(3);
        while (!characters.isEmpty()) {
            int randPicker = random.nextInt(characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
}
