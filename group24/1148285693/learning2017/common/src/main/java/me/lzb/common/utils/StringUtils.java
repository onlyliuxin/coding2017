package me.lzb.common.utils;

/**
 * @author LZB
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    public static String appendSpace(int length, String str) {
        int spaceCount = length - str.length();

        StringBuffer spaceBuffer = new StringBuffer();
        for (int i = 0; i < spaceCount; i++) {
            spaceBuffer.append(" ");
        }
        return str + spaceBuffer.toString();
    }
}
