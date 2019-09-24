package ua.nulp.service.implementation;

import org.junit.Assert;
import org.junit.Test;
import ua.nulp.service.interfaces.CipherService;

public class DirectSubstitutionCipherServiceTest {
    private CipherService cipherService =
            new DirectSubstitutionCipherService(() -> "abcdefghijklmnopqrstuvwxyz .,;-'");

    @Test
    public void decode() {
        String text = "giuifg cei iprc tpnn du cei qprcni";
        String key = "phqgiumeaylnofdxjkrcvstzwb";
        String actual = cipherService.decode(text, key);
        String expected = "defend the east wall of the castle".toUpperCase();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encode() {
        String text = "defend the east wall of the castle";
        String key = "phqgiumeaylnofdxjkrcvstzwb";
        String actual = cipherService.encode(text, key);
        String expected = "giuifg cei iprc tpnn du cei qprcni".toUpperCase();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void encodeAndDecode() {
        String text = "Hello hello are you doing anything I just got to wyeoe you got the good luck is that you " +
                "have the same number of people who don't want you in the world you can help me";
        String key = "qwertyuiopasdfghjklzxcvbnm";
        String encoded = cipherService.encode(text, key);
        String decoded = cipherService.decode(encoded, key);
        Assert.assertEquals(text.toUpperCase(), decoded);
    }
}