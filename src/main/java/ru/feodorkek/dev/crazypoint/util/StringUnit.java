package ru.feodorkek.dev.crazypoint.util;

public class StringUnit {

    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(final String str) {
        return str == null || str.trim().isEmpty();
    }

}
