package ua.nulp.service;

import org.junit.Assert;
import org.junit.Test;
import ua.nulp.service.implementation.TextAnalysingServiceImpl;

import java.util.HashMap;
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
        Map<String, Integer> actual = alphabetService.countCharGroupEntries(text,2,3);
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("AAA", 4);
        expected.put("BBB", 3);
        Assert.assertEquals(expected, actual);
    }

}