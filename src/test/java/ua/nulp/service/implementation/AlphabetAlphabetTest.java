package ua.nulp.service.implementation;

import org.junit.Assert;
import org.junit.Test;
import ua.nulp.service.interfaces.Alphabet;

public class AlphabetAlphabetTest {
    private Alphabet alphabet = new PropertiesAlphabet();

    @Test
    public void get() {
        String expected = "abcdefghijklmnopqrstuvwxyz .,;-'";
        String actual = alphabet.get();
        Assert.assertEquals(expected,actual);
    }
}