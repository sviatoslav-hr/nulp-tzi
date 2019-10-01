package ua.nulp.service.interfaces;

public interface CipherService {
    String decode(String encodedText, Object key);

    String encode(String text, Object key);

    String decode(String text, Object key, String alphabet);

    String encode(String text, Object key, String alphabet);

    String autoDecode(String key);

    String autoDecode(String key, String alphabet);
}
