package com.coding.basic;

import java.util.Arrays;

/**
 * Created by mark on 17/2/24.
 */
public class Array {

    public static Object[] grow(Object[] src, int size) {
        return Arrays.copyOf(src, src.length + size);
//        Object[] target = new Object[src.length + size];
//        System.arraycopy(src, 0, target, 0, src.length);

    }
}
