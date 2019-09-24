package ua.nulp.service.implementation;

import ua.nulp.service.interfaces.AlphabetService;
import ua.nulp.service.interfaces.CipherService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DirectSubstitutionCipherService implements CipherService {
    private AlphabetService alphabetService;

    public DirectSubstitutionCipherService(AlphabetService alphabetService) {
        this.alphabetService = alphabetService;
    }

    @Override
    public String decode(String encodedText, Object key) {
        return decode(encodedText, key, alphabetService.getAlphabet());
    }

    @Override
    public String decode(String encodedText, Object key, String alphabet) {
        return encode(encodedText, alphabet, (String) key);
    }

    @Override
    public String encode(String text, Object key) {
        return encode(text, key, alphabetService.getAlphabet());
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
}
