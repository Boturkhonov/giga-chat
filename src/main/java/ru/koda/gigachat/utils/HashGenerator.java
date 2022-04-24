package ru.koda.gigachat.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Вспомогательный класс для генерации хеш-код.
 */
public class HashGenerator {

    public static String getHash(final String s) {
        return DigestUtils.md5Hex(s);
    }

}
