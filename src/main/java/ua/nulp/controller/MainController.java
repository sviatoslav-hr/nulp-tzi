package ua.nulp.controller;

import ua.nulp.enums.CipherType;
import ua.nulp.service.implementation.cipher.*;
import ua.nulp.service.interfaces.Alphabet;
import ua.nulp.service.interfaces.CipherService;
import ua.nulp.service.interfaces.TextAnalysingService;
import ua.nulp.view.cipher.*;
import ua.nulp.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

import static ua.nulp.service.StringUtils.shuffleString;

public class MainController {
    private MainFrame mainFrame;
    private TextAnalysingService textAnalysingService;
    private CipherService cipherService;
    private Alphabet alphabet;

    public MainController(MainFrame mainFrame, TextAnalysingService textAnalysingService,
                          Alphabet alphabet) {
        this.mainFrame = mainFrame;
        this.textAnalysingService = textAnalysingService;
        this.alphabet = alphabet;
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
                .addActionListener(this::setComboBoxListeners);
    }

    private void setComboBoxListeners(ActionEvent e) {
        JComboBox source = (JComboBox) (e.getSource());
        String selectedItem = (String) source.getSelectedItem();
        if (Objects.equals(selectedItem, CipherType.CAESAR.getName())) {
            selectCaesarCipher();
        } else if (Objects.equals(selectedItem, CipherType.DIRECT_SUBSTITUTION.getName())) {
            selectDirectSubstitutionCipher();
        } else if (Objects.equals(selectedItem, CipherType.VIGENERE.getName())) {
            selectVigenereCipher();
        } else if (Objects.equals(selectedItem, CipherType.HILL.getName())) {
            selectHillCipher();
        } else if (Objects.equals(selectedItem, CipherType.FEISTEL.getName())) {
            selectFeistelCipher();
        } else if (Objects.equals(selectedItem, CipherType.RSA.getName())) {
            selectRSACipher();
        } else if (Objects.equals(selectedItem, CipherType.DES.getName())) {
            selectDESCipher();
        } else {
            unselectCipher();
        }
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
        Map<String, Integer> data = textAnalysingService.countCharGroupEntries(text, 2, length);
        mainFrame.setDiagramPanel(data, limit);
    }

    private void setAlphabet(String text) {
        char[] alphabetArray = textAnalysingService.getAlphabetOf(text).toCharArray();
        mainFrame.getMainPanel().removeCenterComponent();
        mainFrame.getMainPanel().setLabel("Alphabet: " + Arrays.toString(alphabetArray));
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
        setCipherService(new CaesarCipherService(alphabet));
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
        setCipherService(new DirectSubstitutionCipherService(alphabet));
        DirectSubstitutionPanel panel = new DirectSubstitutionPanel(alphabet.get());
        panel.getDecodeButton()
                .addActionListener(e -> solveCipher(panel.getKey(), true));
        panel.getEncodeButton()
                .addActionListener(e -> solveCipher(panel.getKey(), false));
        panel.getRandomButton()
                .addActionListener(e -> panel.getKeyField()
                        .setText(shuffleString(alphabet.get())));
        panel.onEditCell((s, o) -> decodeByTable(panel));
        panel.getTableDecodeButton().addActionListener(e -> decodeByTable(panel));
        mainFrame.getMainPanel().setTopComponent(panel);
        mainFrame.pack();
    }

    private void decodeByTable(DirectSubstitutionPanel panel) {
        String result = ((DirectSubstitutionCipherService) cipherService)
                .decodeByTable(panel.getTableData(), mainFrame.getInputText());
        mainFrame.getMainPanel().setResultArea(result);
        mainFrame.pack();
    }

    private void selectVigenereCipher() {
        unselectCipher();
        setCipherService(new VigenereCipherService(alphabet));
        VigenerePanel panel = new VigenerePanel();
        selectStatisticalCipherPanel(panel);
    }

    private void selectHillCipher() {
        unselectCipher();
        setCipherService(new HillCipherService(alphabet));
        HillCipherPanel panel = new HillCipherPanel();
        selectStatisticalCipherPanel(panel);
    }

    private void selectFeistelCipher() {
        unselectCipher();
        setCipherService(new FeistelCipherService(alphabet));
        FeistelCipherPanel panel = new FeistelCipherPanel();
        selectStatisticalCipherPanel(panel);
    }

    private void selectRSACipher() {
        unselectCipher();
        setCipherService(new RSACipherService(alphabet));
        RSACipherPanel panel = new RSACipherPanel();
        selectStatisticalCipherPanel(panel);
    }

    private void selectDESCipher() {
        unselectCipher();
        setCipherService(new DESCipherService());
        DESCipherPanel panel = new DESCipherPanel();
        selectStatisticalCipherPanel(panel);
    }

    private void selectStatisticalCipherPanel(StatisticalCipherPanel panel) {
        panel.getDecodeButton()
                .addActionListener(e -> solveCipher(panel.getKey(), true));
        panel.getEncodeButton()
                .addActionListener(e -> solveCipher(panel.getKey(), false));
        panel.getStatisticsButton()
                .addActionListener(e -> showCipherStatistics(panel));
        mainFrame.getMainPanel().setTopComponent((JComponent) panel);
        mainFrame.pack();
    }

    private void showCipherStatistics(CipherPanel panel) {
        mainFrame.getMainPanel().removeCenterComponent();
        mainFrame.getMainPanel().removeBottomComponent();
        String text = mainFrame.getInputText();
        String encodedText = cipherService.encode(text, panel.getKey());
        Map<String, List<Integer>> data = textAnalysingService
                .countCharGroupEntries(1, 1, text, encodedText);
        mainFrame.setXyDiagramPanel(data, Integer.MAX_VALUE, "Open", "Encoded");
    }


    private void solveCipher(Object key, boolean decode) {
        mainFrame.getMainPanel().removeCenterComponent();
        mainFrame.getMainPanel().removeBottomComponent();
        String text = mainFrame.getInputText();
        String resultText = decode ? cipherService.decode(text, key)
                : cipherService.encode(text, key);
        mainFrame.getMainPanel().setResultArea(resultText);
        mainFrame.pack();
    }

    private void setCipherService(CipherService cipherService) {
        this.cipherService = cipherService;
    }
}
