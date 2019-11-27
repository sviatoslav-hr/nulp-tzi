package ua.nulp.service.implementation.cipher;

import ua.nulp.service.interfaces.Alphabet;
import ua.nulp.service.interfaces.CipherService;

public class FeistelCipherService implements CipherService {

    private Alphabet alphabet;

    public FeistelCipherService(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    @Override
    public String decode(String text, Object key) {
        return decode(text, key, alphabet.get());
    }

    @Override
    public String encode(String text, Object key) {
        return encode(text, key, alphabet.get());
    }

    @Override
    public String decode(String text, Object key, String alphabet) {
        String[] ints = ((String) key).split(" ");
        if (ints.length != 2) {
            return "";
        }
        int n = Integer.parseInt(ints[0]);
        int k = Integer.parseInt(ints[1]);
        return decode(text, n, k, alphabet);
    }

    @Override
    public String encode(String text, Object key, String alphabet) {
        String[] ints = ((String) key).split(" ");
        if (ints.length != 2) {
            return "";
        }
        int n = Integer.parseInt(ints[0]);
        int k = Integer.parseInt(ints[1]);
        return encode(text, n, k, alphabet);
    }

    private String fixText(String text) {
        text = text.trim();
        if ((text.length() % 2 != 0)) {
            return text + " ";
        } else {
            return text;
        }
    }

    public String encode(String text, int n, int k, String alphabet) {
        text = fixText(text);
        alphabet = alphabet.toUpperCase();
        int mod = alphabet.length();
        StringBuilder encodeText = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            String stringBy2Words = text.substring(i, i + 2);
            int[] twoLettersInNumber = twoWords(stringBy2Words, alphabet);
            int[] encodeNumbers = networkFeistelEncrypt(n, k, twoLettersInNumber, mod);
            String encodeStringBy2Words = encodeTextFromNumbersToString(encodeNumbers, mod);
            encodeText.append(encodeStringBy2Words);
        }
        return String.valueOf(encodeText).toUpperCase();
    }

    private int[] twoWords(String stringBy2Words, String alphabet) {
        int[] arr = new int[2];
        for (int i = 0; i < stringBy2Words.length(); i++) {
            int numberOfLetters = alphabet.indexOf(stringBy2Words.charAt(i));
            arr[i] = numberOfLetters;
        }
        return arr;
    }

    private int[] networkFeistelEncrypt(int n, int k, int[] twoWords, int mod) {
        int[] arr = new int[2];
        int l = twoWords[0];
        int r = twoWords[1];
        int result;
        for (int i = 0; i < n; i++, k--) {
            result = r ^ ((l + k) % mod);
//            101100101
//            111011101
//            ---------
//            111010110 (2) -> 145 (10)

//            00    0
//            01    1
//            10    1
//            11    0
            if (i < n - 1) {
                r = l;
                l = result;
            } else {
                r = result;
            }
            System.out.println("i = " + (i + 1));
            System.out.println("l = " + l);
            System.out.println("r = " + r);
            System.out.println("k = " + k);
            System.out.println("result = " + result);
            System.out.println("----------");
        }
        arr[0] = l;
        arr[1] = r;
        return arr;
    }

    private int[] networkFeistelDecrypt(int n, int k, int[] twoWords, int mod) {
        int[] arr = new int[2];
        int l = twoWords[0];
        int r = twoWords[1];
        k = k - n + 1;
        int result;
        for (int i = 0; i < n; i++, k++) {
            result = r ^ (l + k) % mod;
            if (i < n - 1) {
                r = l;
                l = result;
            } else {
                r = result;
            }
//            System.out.println("i = " + (i + 1));
//            System.out.println("l = " + l);
//            System.out.println("r = " + r);
//            System.out.println("k = " + k);
//            System.out.println("result = " + result);
//            System.out.println("----------");
        }
        arr[0] = l;
        arr[1] = r;
        return arr;
    }

    private String encodeTextFromNumbersToString(int[] arr, int mod) {
        StringBuilder str = new StringBuilder();
        for (int value : arr) {
            int index = value % mod;
//            System.out.println("index = " + index);
            str.append(alphabet.get().charAt(index));
        }
//        System.out.println("str = " + str);
        return str.toString();
    }

    public String decode(String text, int n, int k, String alphabet) {
        text = text.toUpperCase();
        alphabet = alphabet.toUpperCase();
        int mod = alphabet.length();
        StringBuilder decodeText = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            String stringBy2Words = text.substring(i, i + 2);
            int[] twoLettersInNumber = twoWords(stringBy2Words, alphabet);
            int[] decodeNumbers = networkFeistelDecrypt(n, k, twoLettersInNumber, mod);
            String decodeStringBy2Words = encodeTextFromNumbersToString(decodeNumbers, mod);
            decodeText.append(decodeStringBy2Words);
        }
        return decodeText.toString().toUpperCase().trim();
    }
}