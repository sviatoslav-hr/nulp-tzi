package ua.nulp.service.implementation.cipher;

import org.junit.Assert;
import org.junit.Test;
import ua.nulp.service.implementation.cipher.VigenereCipherService;
import ua.nulp.service.interfaces.CipherService;

public class VigenereCipherServiceTest {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private CipherService cipherService = new VigenereCipherService(() -> alphabet);
    private String plainText = "hello hello are you doing anything i just got to wyeoe you got the good luck is that " +
            "you have the same number of people who don't want you in the world you can help me";
    private String key = "sviatoslavkhrystyna";

    @Test
    public void decode() {
        String text = "zztlh vwwlj kyv wgn bbifb inrhztnb s qlql zmg tg rgehs qzu bya kfw zmbd dpkk bg lsao ivl " +
                "fsoc ghw nimx bmxbzb vw nwhnye ocw dhb'l haid ffs ag rue ojzlw mgf cvx ovjh fc";
        String actual = cipherService.decode(text, key);
        Assert.assertEquals(plainText.toUpperCase(), actual);
    }

    @Test
    public void encode() {
        String expected = "zztlh vwwlj kyv wgn bbifb inrhztnb s qlql zmg tg rgehs qzu bya kfw zmbd dpkk bg " +
                "lsao ivl fsoc ghw nimx bmxbzb vw nwhnye ocw dhb'l haid ffs ag rue ojzlw mgf cvx ovjh fc";
        String actual = cipherService.encode(plainText, key);
        Assert.assertEquals(expected.toUpperCase(), actual);
    }

    @Test
    public void encodeAndDecode() {
        String text = "HELLO HELLO ARE YOU DOING ANYTHING I JUST GOT TO WYEOE YOU GOT THE GOOD LUCK IS THAT YOU " +
                "HAVE THE SAME NUMBER OF PEOPLE WHO DON'T WANT YOU IN THE WORLD YOU CAN HELP ME";
        String key = "sviatoslavkhrystyna";
        String encodedText = cipherService.encode(plainText, key);
        String decodedText = cipherService.decode(encodedText, key);
        Assert.assertEquals(text.toUpperCase(), decodedText);
    }

}