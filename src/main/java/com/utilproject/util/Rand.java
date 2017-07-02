package com.utilproject.util;

import java.util.Random;

/**
 *
 * @author Zygimantas
 */
public class Rand {

    // http://stackoverflow.com/a/5683362/1766166
    public static String randomString() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz.@".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();

        return output;
    }
}
