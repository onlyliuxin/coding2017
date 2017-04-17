package jvm.util;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class ByteUtils {
    public static String toHexString(byte[] bytes, int off, int len) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            builder.append(Integer.toHexString(Byte.toUnsignedInt(bytes[off + i])));
        }
        return builder.toString();
    }

    public static int toInt(byte[] bytes, int off, int len) {
        return Integer.parseInt(toHexString(bytes, off, len), 16);
    }
}
