package com.coding.common.util;

import org.junit.Test;

/**
 * Created by luoziyihao on 5/2/17.
 */
public class ByteUtilsTest {

    @Test
    public void testByteToHexString(){
        byte[] bytes = new byte[]{
                1,
                (byte) 255
        };
        System.out.println(ByteUtils.byteToHexString(bytes));
        System.out.println(Integer.toHexString(255));
    }

}