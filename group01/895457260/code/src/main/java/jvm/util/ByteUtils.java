package jvm.util;

import jvm.util.code.Code;
import jvm.util.code.CompCode;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class ByteUtils {
    public static String toHexString(byte[] bytes) {
        return toHexString(bytes, 0, bytes.length);
    }

    public static String toHexString(byte[] bytes, int off, int len) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            int uInt = Byte.toUnsignedInt(bytes[off + i]);
            String hex = Integer.toHexString(uInt);
            builder.append(hex.length() < 2 ? '0' + hex : hex);
        }
        return builder.toString();
    }

    public static int toUnsignedInt(byte[] bytes) {
        return toUnsignedInt(bytes, 0, bytes.length);
    }

    public static int toUnsignedInt(byte[] bytes, int off, int len) {
        return Integer.parseInt(toHexString(bytes, off, len), 16);
    }

    public static float toFloat(byte[] bytes) {
        return toFloat(bytes, 0, bytes.length);
    }

    public static float toFloat(byte[] bytes, int off, int len) {
        return Float.intBitsToFloat(toUnsignedInt(bytes, off, len));
    }
}
