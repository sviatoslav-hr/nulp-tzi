package ua.nulp.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class StringUtils {
    private StringUtils() {
    }

    public static String shuffleString(String input) {
        List<String> characters = new LinkedList<>(Arrays.asList(input.split("")));
        StringBuilder output = new StringBuilder(input.length());
        Random random = new Random();
        while (!characters.isEmpty()) {
            int randPicker = random.nextInt(characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
}
