package com.example.mbraconnier.yakoi;

/**
 * Created by mbraconnier on 17/01/2018.
 */

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import android.util.Base64;

public class PasswordFormat {
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String getPasswordFormat(String password, String login) throws NoSuchAlgorithmException {
        int length = password.length();

        String hashPassword = '#' + Integer.toString(length) + '$' + login + password;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(hashPassword.getBytes(StandardCharsets.UTF_8));

        String encodedHex = bytesToHex(hash);
        return encodedHex;
    }
}
