package ua.nulp.service.implementation.cipher;

import ua.nulp.service.interfaces.CipherService;
import ua.nulp.service.interfaces.Alphabet;

import java.util.List;
import java.util.Map;

import static ua.nulp.service.implementation.cipher.DirectSubstitutionCipherService.getStringListMap;

public class CaesarCipherService implements CipherService {
    private Alphabet alphabet;

    public CaesarCipherService(Alphabet alphabet) {
        this.alphabet = alphabet;
    }


    @Override
    public String decode(String text, Object key) {
        return decode(text, key, alphabet.get());
    }

    @Override
    public String encode(String text, Object key) {
        return encode(text, key, alphabet.get());
    }

    @Override
    public String decode(String text, Object key, String alphabet) {
        return encode(text, -(Integer) key, alphabet);
    }

    @Override
    public String encode(String text, Object key, String alphabet) {
        int shift = (int) key;
        text = text.replace("\n", "").toLowerCase();
        Map<String, List<Integer>> charsPositions = getCharsPositions(text, alphabet);
        char[] textArray = text.toCharArray();
        for (int i = 0; i < alphabet.length(); i++) {
            char currentChar = alphabet.charAt(i);
            int newCharIndex = i + shift;
            if (newCharIndex >= alphabet.length()) {
                newCharIndex %= alphabet.length();
            } else if (newCharIndex < 0) {
                if (alphabet.length() + newCharIndex >= 0) {
                    newCharIndex += alphabet.length();
                } else {
                    int subtrahend = (newCharIndex % alphabet.length());
                    newCharIndex = alphabet.length() + subtrahend;
                }
            }
            char newChar = alphabet.charAt(newCharIndex);
            charsPositions.get(String.valueOf(currentChar))
                    .forEach(integer -> textArray[integer] = newChar);
        }
        return String.valueOf(textArray).toUpperCase();
    }

    private Map<String, List<Integer>> getCharsPositions(String text, String alphabet) {
        return getStringListMap(text, alphabet);
    }
}
