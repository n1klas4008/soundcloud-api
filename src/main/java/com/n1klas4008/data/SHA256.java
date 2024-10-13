package com.n1klas4008.data;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    public static String hash(String plain) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return plain;
        }
        byte[] hash = digest.digest(plain.getBytes(StandardCharsets.UTF_8));
        return byteToHex(hash);
    }

    private static String byteToHex(byte[] hash) {
        StringBuilder builder = new StringBuilder();
        for (byte character : hash) {
            String hex = Integer.toHexString(0xff & character);
            if (hex.length() == 1)
                builder.append('0');
            builder.append(hex);
        }
        return builder.toString();
    }
}
