package com.coding2017.jvm.util;

/**
 * Created by kaitao.li on 2017/4/16.
 */
public class ByteUtil {

    public static int bytesToInt(byte[] data) {
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            result = result * 256 + data[i];
        }
        return result;
    }

    public static String byteToHexString(byte[] codes) {
        StringBuffer buffer = new StringBuffer();
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
