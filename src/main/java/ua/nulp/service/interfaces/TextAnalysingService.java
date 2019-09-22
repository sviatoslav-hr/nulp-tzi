package ua.nulp.service.interfaces;

import java.util.Map;

public interface TextAnalysingService {

    String getAlphabetOf(String text);

    Map<Character, Integer> getCharAmount(String text);

    Map<String, Integer> countAlphabetEntries(String text);

    Map<String, Integer> countCharGroupEntries(String text, int minEntries, int charGroupLength);

    String decodeCesarCipher(String text, int shift);

    String decodeCesarCipher(String text, int shift, String alphabet);

    String encodeCesarCipher(String text, int shift);

    String encodeCesarCipher(String text, int shift, String alphabet);

}
