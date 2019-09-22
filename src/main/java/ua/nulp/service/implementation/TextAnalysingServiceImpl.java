package ua.nulp.service.implementation;

import ua.nulp.service.interfaces.TextAnalysingService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextAnalysingServiceImpl implements TextAnalysingService {
    private static final String CESAR_ALPHABET = "abcdefghijklmnopqrstuvwxyz .,;-'";

    public String getAlphabetOf(String text) {
        return List.of(text.split(""))
                .stream()
                .distinct()
                .sorted((o1, o2) -> {
                    char o1Char = o1.charAt(0);
                    char o2Char = o2.charAt(0);
                    if (Character.isLetter(o1Char) && Character.isLetter(o2Char)) {
                        return o1.compareTo(o2);
                    } else if (Character.isLetter(o1Char)) {
                        return -1;
                    } else if (Character.isLetter(o2Char)) {
                        return 1;
                    } else {
                        return o1.compareTo(o2);
                    }
                })
                .collect(Collectors.joining());
    }

    public Map<Character, Integer> getCharAmount(String text) {
        String alphabet = getAlphabetOf(text);
        Map<Character, Integer> map = new HashMap<>();
        List.of(alphabet.split(""))
                .forEach(character -> map.put(
                        character.charAt(0),
                        (int) List.of(text.split(""))
                                .stream()
                                .filter(character::contains)
                                .count()));
        return map;
    }

    public Map<String, Integer> countAlphabetEntries(String text) {
        return countCharGroupEntries(text, 1, 1);
    }

    public Map<String, Integer> countCharGroupEntries(String text, int minEntries, int charGroupLength) {
        text = text.replace(" ", "_");
        Map<String, Integer> map = new HashMap<>();
        int length = text.length();
        for (int i = 0; i + charGroupLength < length; i++) {
            String charGroup = text.substring(i, i + charGroupLength);
            int substringsLength = length - text.replace(charGroup, "").length();
            if (substringsLength / charGroupLength >= minEntries) {
                map.put(charGroup, substringsLength / charGroupLength);
            }
        }
        return map;
    }

    @Override
    public String encodeCesarCipher(String text, int shift) {
        return encodeCesarCipher(text, shift, CESAR_ALPHABET);
    }

    @Override
    public String encodeCesarCipher(String text, int shift, String alphabet) {
        text = text.replace("\n", "").toLowerCase();
        Map<String, List<Integer>> charsPositions = getCharsPositions(text);
        char[] textArray = text.toCharArray();
        for (int i = 0; i < alphabet.length(); i++) {
            char currentChar = alphabet.charAt(i);
            int newCharIndex = i + shift;
            if (newCharIndex >= alphabet.length()) {
                newCharIndex -= alphabet.length();
            } else if (newCharIndex < 0) {
                newCharIndex += alphabet.length();
            }
            char newChar = alphabet.charAt(newCharIndex);
            charsPositions.get(String.valueOf(currentChar))
                    .forEach(integer -> textArray[integer] = newChar);
        }
        return String.valueOf(textArray).toUpperCase();
    }

    @Override
    public String decodeCesarCipher(String text, int shift) {
        return decodeCesarCipher(text, shift, CESAR_ALPHABET);
    }

    @Override
    public String decodeCesarCipher(String text, int shift, String alphabet) {
        return encodeCesarCipher(text, -shift, alphabet);
    }

    private Map<String, List<Integer>> getCharsPositions(String text) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < CESAR_ALPHABET.length(); i++) {
            int position = -1;
            char currentChar = CESAR_ALPHABET.charAt(i);
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
