package ua.nulp.service.implementation;

import org.junit.Assert;
import org.junit.Test;
import ua.nulp.service.interfaces.AlphabetService;

public class AlphabetAlphabetServiceTest {
    private AlphabetService alphabetService = new AlphabetPropertiesService();

    @Test
    public void get() {
        String expected = "abcdefghijklmnopqrstuvwxyz .,;-'";
        String actual = alphabetService.getAlphabet();
        Assert.assertEquals(expected,actual);
    }
}