package com.example.recruitservice.utils;

public class StringUtils {

    public static boolean isValidWithMaxLength(String str, int length) {
        return str != null && str.length() <= length;
    }

    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

}
