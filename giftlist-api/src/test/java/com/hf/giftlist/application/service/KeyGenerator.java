package com.hf.giftlist.application.service;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

class KeyGenerator {

    public void createKeys() throws NoSuchAlgorithmException {

        javax.crypto.KeyGenerator gen = javax.crypto.KeyGenerator.getInstance("AES");
        gen.init(256);
        SecretKey encryptKey = gen.generateKey();


        javax.crypto.KeyGenerator gen2 = javax.crypto.KeyGenerator.getInstance("HmacSHA256");
        gen2.init(256);
        SecretKey signKey = gen2.generateKey();

        System.out.println(Base64.getEncoder().encodeToString(encryptKey.getEncoded()));
        System.out.println(Base64.getEncoder().encodeToString(signKey.getEncoded()));
    }

}