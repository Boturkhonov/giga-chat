package ru.koda.gigachat.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class HashGenerator {

    public static String getHash(final String s) {
        return DigestUtils.md5Hex(s);
    }

}
