package ua.nulp.service.implementation;

import ua.nulp.service.interfaces.AlphabetService;
import ua.nulp.service.interfaces.CipherService;

public class VigenereCipherService implements CipherService {
    private AlphabetService alphabetService;

    public VigenereCipherService(AlphabetService alphabetService) {
        this.alphabetService = alphabetService;
    }

    @Override
    public String decode(String encodedText, Object key) {
        return decode(encodedText, key, alphabetService.getAlphabet());
    }

    @Override
    public String encode(String text, Object key) {
        return encode(text, key, alphabetService.getAlphabet());
    }

    @Override
    public String decode(String text, Object key, String alphabet) {
        return encodeVigenere(text, (String) key,alphabet,true);
    }

    @Override
    public String encode(String text, Object key, String alphabet) {
        return encodeVigenere(text, (String) key, alphabet, false);
    }

    @Override
    public String autoDecode(String key) {
        return null;
    }

    @Override
    public String autoDecode(String key, String alphabet) {
        return null;
    }

    private String encodeVigenere(String text, String key, String alphabet, boolean isEncoded) {
        text = text.toLowerCase();
        key = key.toLowerCase();
        alphabet = alphabet.toLowerCase();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); ) {
            int textCharIndex = alphabet.indexOf(text.charAt(i));
            int keyCharIndex = alphabet.indexOf(key.charAt(i % key.length()));
            if (textCharIndex < 0 || keyCharIndex < 0) {
                builder.append(text.charAt(i));
                text = text.substring(0, i) + text.substring(i + 1);
                continue;
            }
            int encodedCharIndex = isEncoded ?
                    (textCharIndex - keyCharIndex + alphabet.length()) % alphabet.length()
                    : (textCharIndex + keyCharIndex) % alphabet.length();
            char encodedChar = alphabet.charAt(encodedCharIndex);
            builder.append(encodedChar);
            i++;
        }
        return builder.toString().toUpperCase();

    }
}
