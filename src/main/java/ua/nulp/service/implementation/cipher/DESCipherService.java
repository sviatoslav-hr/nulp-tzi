package ua.nulp.service.implementation.cipher;

import ua.nulp.service.interfaces.CipherService;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DESCipherService implements CipherService {
    private static final String ALGORITHM = "DES";
    private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";

    @Override
    public String decode(String text, Object key) {
        return decrypt(text, (String) key);
    }

    @Override
    public String encode(String text, Object key) {
        return encrypt(text, (String) key);
    }

    @Override
    public String decode(String text, Object key, String alphabet) {
        return decrypt(text, (String) key);
    }

    @Override
    public String encode(String text, Object key, String alphabet) {
        return encrypt(text, (String) key);
    }

    private static String encrypt(String input, String key) {
        key = key.substring(0, 8).toLowerCase();
        Cipher cipher = getInstance(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = new byte[0];
        try {
            bytes = cipher.doFinal(input.getBytes());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(Base64.getEncoder().encode(bytes));
    }

    private static Cipher getInstance(int mode, String key) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(TRANSFORMATION);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        try {
            assert cipher != null;
            cipher.init(mode, sks, iv);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return cipher;
    }


    private static String decrypt(String input, String key) {
        key = key.substring(0, 8).toLowerCase();
        Cipher cipher = getInstance(Cipher.DECRYPT_MODE, key);
        byte[] bytes = new byte[0];
        try {
            bytes = cipher.doFinal(Base64.getDecoder().decode(input));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }
}
