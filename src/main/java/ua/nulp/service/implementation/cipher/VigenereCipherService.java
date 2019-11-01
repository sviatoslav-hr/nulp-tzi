package ua.nulp.service.implementation.cipher;

import ua.nulp.service.interfaces.Alphabet;
import ua.nulp.service.interfaces.CipherService;

public class VigenereCipherService implements CipherService {
    private Alphabet alphabet;

    public VigenereCipherService(Alphabet alphabet) {
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
        return encodeVigenere(text, (String) key,alphabet,true);
    }

    @Override
    public String encode(String text, Object key, String alphabet) {
        return encodeVigenere(text, (String) key, alphabet, false);
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
