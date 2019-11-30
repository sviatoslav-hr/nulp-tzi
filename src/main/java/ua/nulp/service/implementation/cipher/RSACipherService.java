package ua.nulp.service.implementation.cipher;

import ua.nulp.service.interfaces.Alphabet;
import ua.nulp.service.interfaces.CipherService;

import java.math.BigInteger;
import java.util.Arrays;

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

    private String crypt(int p, int q, String text, String alphabet) {
        alphabet = alphabet.toUpperCase();
        int n = p * q;
        long m = (p - 1L) * (q - 1L);
        double d = calculateD(m);
        long e = calculateE(d, m);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= text.length(); i += 3) {
            String substring = i + 3 > text.length()
                    ? text.substring(i)
                    : text.substring(i, i + 3);
            int[] indexes = getIndexesFromString(substring, alphabet);
            for (int index : indexes) {
                BigInteger encodedIndex = encodeCharIndex(index, e, n);
                result.append(encodedIndex).append(" ");
            }
        }
        return result.toString().trim();
    }

    private int[] getIndexesFromString(String substring, String alphabet) {
        if (substring.length() < 3) {
            return getIndexesFromString(substring + " ", alphabet);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < substring.length(); i++) {
            int index = alphabet.indexOf(substring.charAt(i));
            stringBuilder.append(index < 10
                    ? "0" + index
                    : index);
        }
        String s = stringBuilder.toString();
        int center = s.length() / 2;
        int part1 = Integer.parseInt(s.substring(0, center));
        int part2 = Integer.parseInt(s.substring(center));
        return new int[]{part1, part2};
    }

    private BigInteger encodeCharIndex(int index, long e, int n) {
        BigInteger bi = BigInteger.valueOf(index);
        bi = bi.pow((int) e);
        bi = bi.mod(BigInteger.valueOf(n));
        return bi;
    }

    private String decrypt(int p, int q, String text, String alphabet) {
        alphabet = alphabet.toUpperCase();
        int n = p * q;
        long m = (p - 1L) * (q - 1L);
        double d = calculateD(m);
        StringBuilder openText = new StringBuilder();
        String[] codes = text.split(" ");
        for (int i = 0; i + 1 < codes.length; i += 2) {
            int[] indexes = {Integer.parseInt(codes[i]),
                    Integer.parseInt(codes[i + 1])};
            decodeCharIndexes(indexes, d, n);
            openText.append(getStringFromIndexes(indexes, alphabet));
        }
        return openText.toString().trim();
    }

    private void decodeCharIndexes(int[] indexes, double d, int n) {
        for (int i = 0; i < indexes.length; i++) {
            BigInteger index = BigInteger.valueOf(indexes[i]);
            index = index.pow((int) d);
            indexes[i] = index.mod(BigInteger.valueOf(n))
                    .intValue();
        }
    }

    private String getStringFromIndexes(int[] parts, String alphabet) {
        if (parts.length <= 1) {
            return "";
        } else if (parts.length > 2) {
            return getStringFromIndexes(Arrays.copyOfRange(parts, 0, 2), alphabet);
        }
        StringBuilder indexesBuilder = new StringBuilder();
        for (int part : parts) {
            String partString = String.valueOf(part);
            if (partString.length() == 1) {
                partString = "00" + partString;
            } else if (partString.length() == 2) {
                partString = "0" + partString;
            }
            indexesBuilder.append(partString);
        }
        String s = indexesBuilder.toString();
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i + 1 < s.length(); i += 2) {
            int index = Integer.parseInt(s.substring(i, i + 2));
            index %= alphabet.length();
            resultBuilder.append(alphabet.charAt(index));
        }
        return resultBuilder.toString();
    }

    private long calculateE(double d, double m) {
        long e = 10;
        while (true) {
            if ((e * d) % m == 1)
                break;
            else
                e++;
        }
        return e;
    }

    private double calculateD(double m) {
        double d = m - 1;
        for (long i = 2; i <= m; i++)
            if ((m % i == 0) && (d % i == 0)) {
                d--;
                i = 1;
            }
        return d;
    }
}
