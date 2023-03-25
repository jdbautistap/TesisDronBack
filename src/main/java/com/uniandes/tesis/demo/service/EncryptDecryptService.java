package com.uniandes.tesis.demo.service;

import org.springframework.stereotype.Service;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

@Service
public class EncryptDecryptService {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "ThisIsASecretKey";

    public static String encrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(EncryptDecryptService.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedByteValue = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedByteValue);
    }

    public static String decrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(EncryptDecryptService.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedByteValue = cipher.doFinal(Base64.getDecoder().decode(value));
        return new String(decryptedByteValue, StandardCharsets.UTF_8);
    }

    private static Key generateKey() throws Exception {
        return new SecretKeySpec(EncryptDecryptService.KEY.getBytes(), EncryptDecryptService.ALGORITHM);
    }
}
