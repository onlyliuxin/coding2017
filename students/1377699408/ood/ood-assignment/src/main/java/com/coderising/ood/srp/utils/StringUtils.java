package com.coderising.ood.srp.utils;

public class StringUtils {
    public static boolean isBlank(String s) {
        return s == null || "".equals(s.trim());
    }
}
