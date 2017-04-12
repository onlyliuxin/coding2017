package jvm.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Haochen on 2017/3/26.
 * TODO:
 */
public class ArrayUtils {
    public static Collection<Byte> toList(byte[] array, int start, int length){
        Collection<Byte> list = new ArrayList<>();
        byte[] newArray = new byte[length];
        System.arraycopy(array, start, newArray, 0, length);
        for (byte b : newArray) {
            list.add(b);
        }
        return list;
    }

    public static byte[] toArray(Collection<Byte> c) {
        byte[] bytes = new byte[c.size()];
        int pos = 0;
        for (byte b : c) {
            bytes[pos++] = b;
        }
        return bytes;
    }
}
