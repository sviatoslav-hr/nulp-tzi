package ua.nulp.service.implementation.cipher;

import ua.nulp.service.interfaces.Alphabet;
import ua.nulp.service.interfaces.CipherService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DirectSubstitutionCipherService implements CipherService {
    private Alphabet alphabet;


    public DirectSubstitutionCipherService(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    @Override
    public String decode(String text, Object key) {
        return decode(text, key, alphabet.get());
    }

    @Override
    public String decode(String text, Object key, String alphabet) {
        return encode(text, alphabet, (String) key);
    }

    @Override
    public String encode(String text, Object key) {
        return encode(text, key, alphabet.get());
    }

    @Override
    public String encode(String text, Object key, String alphabet) {
        String newAlphabet = (String) key;
        if (newAlphabet.length() != alphabet.length()) {
            throw new IllegalArgumentException("Incompatible newAlphabet and alphabet");
        }
        text = text.replace("\n", "").toLowerCase();
        Map<String, List<Integer>> charsPositions = getCharsPositions(text, alphabet);
        char[] textArray = text.toCharArray();
        for (int i = 0; i < alphabet.length(); i++) {
            char currentChar = alphabet.charAt(i);
            char newChar = newAlphabet.charAt(i);
            charsPositions.get(String.valueOf(currentChar))
                    .forEach(integer -> textArray[integer] = newChar);
        }
        return String.valueOf(textArray).toUpperCase();
    }

    private Map<String, List<Integer>> getCharsPositions(String text, String alphabet) {
        return getStringListMap(text, alphabet);
    }

    static Map<String, List<Integer>> getStringListMap(String text, String alphabet) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < alphabet.length(); i++) {
            int position = -1;
            char currentChar = alphabet.charAt(i);
            List<Integer> positions = new LinkedList<>();
            do {
                position = text.indexOf(currentChar, position + 1);
                if (position >= 0) {
                    positions.add(position);
                }
            } while (position >= 0);
            map.put(String.valueOf(currentChar), positions);
        }
        return map;
    }

    public String decodeByTable(String[][] data, String text) {
        text = text.replace("\n","").toLowerCase();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            String replacingChar = getSymbol(data, currentChar);
            builder.append(replacingChar);
        }
        return builder.toString();
    }

    private String getSymbol(String[][] data, char c) {
        for (String[] datum : data) {
            if (datum[0].contains(String.valueOf(c))) {
                String res = datum[1].substring(0, 1);
                return datum[0].equals(datum[1]) ? res : res.toUpperCase();
            }
        }
        return String.valueOf(c);
    }
}
