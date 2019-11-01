package ua.nulp.service.implementation.cipher;

import ua.nulp.service.interfaces.Alphabet;
import ua.nulp.service.interfaces.CipherService;

import java.math.BigInteger;

public class RSACipherService implements CipherService {
    private Alphabet alphabet;

    public RSACipherService(Alphabet alphabet) {
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
        int p = Integer.parseInt(ints[0]);
        int q = Integer.parseInt(ints[1]);
        return decrypt(p, q, text, alphabet);
    }

    @Override
    public String encode(String text, Object key, String alphabet) {
        String[] ints = ((String) key).split(" ");
        if (ints.length != 2) {
            return "";
        }
        int p = Integer.parseInt(ints[0]);
        int q = Integer.parseInt(ints[1]);
        return crypt(p, q, text, alphabet);
    }

    private static String crypt(int p, int q, String text, String alphabet) {
        alphabet = alphabet.toUpperCase();
        int n = p * q;
        int m = (p - 1) * (q - 1);
        long d = calculateD(m);
        long e = calculateE(d, m);
        StringBuilder result = new StringBuilder();
        BigInteger bi;
        for (int i = 0; i < text.length(); i++) {
            int index = alphabet.indexOf(text.charAt(i));
            bi = new BigInteger(String.valueOf(index));
            bi = bi.pow((int) e);
            bi = bi.mod(BigInteger.valueOf(n));
            result.append(bi).append(" ");
        }
        return result.toString();

    }

    private static String decrypt(int p, int q, String text, String alphabet) {
        alphabet = alphabet.toUpperCase();
        int n = p * q;
        int m = (p - 1) * (q - 1);
        long d = calculateD(m);
        StringBuilder openText = new StringBuilder();
        String[] codes = text.split(" ");
        BigInteger bi;
        for (String item : codes) {
            bi = new BigInteger(item);
            bi = bi.pow((int) d);
            bi = bi.mod(BigInteger.valueOf(n));
            int index = bi.intValue();
            openText.append(alphabet.charAt(index));
        }
        return openText.toString();

    }

    private static long calculateE(long d, long m) {
        long e = 10;
        while (true) {
            if ((e * d) % m == 1)
                break;
            else
                e++;
        }
        return e;
    }

    private static long calculateD(long m) {
        long d = m - 1;

        for (long i = 2; i <= m; i++)
            if ((m % i == 0) && (d % i == 0)) {
                d--;
                i = 1;
            }

        return d;
    }
}
