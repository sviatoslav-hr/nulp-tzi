package ua.nulp.service.implementation;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextAnalysingServiceImplTest {
    private TextAnalysingServiceImpl alphabetService = new TextAnalysingServiceImpl();

    @Test
    public void getAlphabetOf() {
        String text = "aslkdjksadjkl*s&a.d324,'";
        TextAnalysingServiceImpl alphabetService = new TextAnalysingServiceImpl();
        String actual = alphabetService.getAlphabetOf(text);
        String expected = "adjkls&'*,.234";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCharAmount() {
        String text = "abcabaabcabaabcaba";
        Map<Character, Integer> actual = alphabetService.getCharAmount(text);
        HashMap<Character, Integer> expected = new HashMap<>();
        expected.put('a', 9);
        expected.put('b', 6);
        expected.put('c', 3);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countCharGroupsEntries() {
        TextAnalysingServiceImpl alphabetService = new TextAnalysingServiceImpl();
        String text = "AAAqBBBwAAAeBBBAAABBBrAAA";
        Map<String, Integer> actual = alphabetService.countCharGroupEntries(text, 2, 3);
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("AAA", 4);
        expected.put("BBB", 3);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countCharGroupsEntriesWithVarargs() {
        TextAnalysingServiceImpl alphabetService = new TextAnalysingServiceImpl();
        String text1 = "aaabbcccc";
        String text2 = "abbbcc";
        Map<String, List<Integer>> actual = alphabetService
                .countCharGroupEntries(1, 1, text1, text2);
        HashMap<String, List<Integer>> expected = new HashMap<>();
        expected.put("a", Arrays.asList(3, 1));
        expected.put("b", Arrays.asList(2, 3));
        expected.put("c", Arrays.asList(4, 2));
        Assert.assertEquals(expected, actual);
    }

}