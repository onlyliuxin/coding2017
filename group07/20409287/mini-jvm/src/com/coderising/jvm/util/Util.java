package com.coderising.jvm.util;

public class Util {

    public static int byteToInt(byte[] codes) {
        String s1 = byteToHexString(codes);
        return Integer.valueOf(s1, 16);
    }


    public static String byteToHexString(byte[] codes) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < codes.length; i++) {
            byte b = codes[i];
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            buffer.append(strHex);
        }

        return buffer.toString();
    }
}
