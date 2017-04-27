package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

/**
 * Created by yrs on 2017/4/9.
 */
public class Test {
    public static void main(String [] args) {
        byte[] codes = new byte[4];
        codes[0] = 0;
        codes[1] = 0;
        codes[2] = 0;
        codes[3] = 52;
        System.out.println(Util.byteToHexString(codes));
        System.out.println(Util.byteToInt(codes));

        byte b = 99;
        System.out.println((char)b);
    }
}
                