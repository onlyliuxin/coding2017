package com.myutil;

import java.util.regex.Pattern;

/**
 * javaBean工具
 */
public class JavaBeanUtil {
    public static String getGetMethodName(String attributeName) {
        if ((Character.isLowerCase(attributeName.charAt(0)) && Character.isUpperCase(attributeName.charAt(1)))
                || (Character.isUpperCase(attributeName.charAt(0)))) {
            return "get" + attributeName;
        } else if (attributeName.indexOf("is") == 0 && Character.isUpperCase(attributeName.charAt(1))) {
            return attributeName;
        } else {
            char[] chars = attributeName.toCharArray();
            chars[0] -= 32;
            return "get" + String.valueOf(chars);
        }
    }

    public static String getSetMethodName(String attributeName) {
        if ((Character.isLowerCase(attributeName.charAt(0)) && Character.isUpperCase(attributeName.charAt(1)))
                || (Character.isUpperCase(attributeName.charAt(0)))) {
            return "set" + attributeName;
        } else if (attributeName.indexOf("is") == 0 && Character.isUpperCase(attributeName.charAt(2))) {
            return "set" + attributeName.replace("is", "");
        } else {
            char[] chars = attributeName.toCharArray();
            chars[0] -= 32;
            return "set" + String.valueOf(chars);
        }
    }

}
