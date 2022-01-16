package com.example.spring.learn.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class EncryptUtil {
    private static final Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

    private static final String SALT = "SALT";
    private static final String AES_CODE = "123456";
    private static final String CONTENT_CHARSET = "utf-8";

    // 单向加密，MD5加密
    public static String encryptByMd5(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        byte[] bytes = (content + SALT).getBytes();
        return DigestUtils.md5DigestAsHex(bytes);
    }

    // 双向加密，对称加解密，AES加密
    public static String encryptByAES(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            byte[] data = doAES(Cipher.ENCRYPT_MODE, content.getBytes(CONTENT_CHARSET), AES_CODE.getBytes());
            return new String(new Base64().encode(data), CONTENT_CHARSET);
        } catch (Exception e) {
            logger.error("encryptByAES error", e);
            return null;
        }
    }

    // 双向加密，对称加解密，AES解密
    public static String decryptByAES(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        try {
            byte[] src = new Base64().decode(content);
            byte[] data = doAES(Cipher.DECRYPT_MODE, src, AES_CODE.getBytes());
            return new String(data, CONTENT_CHARSET);
        } catch (Exception e) {
            logger.error("decryptByAES error", e);
            return null;
        }
    }

    private static byte[] doAES(int mode, byte[] data, byte[] key) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key);
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode, keySpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            logger.error("doAES error", e);
            return null;
        }
    }

    // 双向加密，非对称加解密，RSA算法，加密
    public static String encryptByRSA(RSAPublicKey rsaPublicKey, String content) {
        try {
            if (rsaPublicKey != null) {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
                byte[] data = cipher.doFinal(content.getBytes(CONTENT_CHARSET));
                return new String(new Base64().encode(data), CONTENT_CHARSET);
            }
        } catch (Exception e) {
            logger.error("encryptByRSA error", e);
        }
        return null;
    }

    // 双向加密，非对称加解密，RSA算法，解密
    public static String decryptByRSA(RSAPrivateKey rsaPrivateKey, String content) {
        try {
            if (rsaPrivateKey != null) {
                byte[] data = new Base64().decode(content);
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
                return new String(cipher.doFinal(data), CONTENT_CHARSET);
            }
        } catch (Exception e) {
            logger.error("decryptByRSA error", e);
        }
        return null;
    }

    private static KeyPair generateKeyPairByRSA() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(1024);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            logger.error("generateKeyPairByRSA error", e);
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        String content = "1234567abcde";

        // md5测试
        System.out.println(encryptByMd5(content));

        // AES测试
        System.out.println("AES明文: " + content);
        String aesString = encryptByAES(content);
        System.out.println("AES加密后: " + aesString);
        System.out.println("AES解密后: " + decryptByAES(aesString));

        // RSA测试
        KeyPair keyPair = generateKeyPairByRSA();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        System.out.println("RSA私钥: " + new String(new Base64().encode(rsaPrivateKey.getEncoded()), CONTENT_CHARSET));
        System.out.println("RSA公钥: " + new String(new Base64().encode(rsaPublicKey.getEncoded()), CONTENT_CHARSET));
        System.out.println("明文: " + content);
        String rsaString = encryptByRSA(rsaPublicKey, content);
        System.out.println("RSA加密后: " + rsaString);
        System.out.println("RSA解密后: " + decryptByRSA(rsaPrivateKey, rsaString));

    }

}
