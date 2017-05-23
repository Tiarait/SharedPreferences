package com.example.tiar.task;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tiar on 20.05.2017.
 */

public class Utils {
    public static final Pattern pattern = Pattern.compile("^([a-z0-9_-]+.)*[a-z0-9_-]+(.[a-z0-9_-]+)*.[a-z]{2,6}$");

    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    public static boolean valid(String s) {
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
