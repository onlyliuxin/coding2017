package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

import java.util.Arrays;

public class ByteCodeIterator {

    private byte[] byteCode;

    private int index = 0;

    public ByteCodeIterator(byte[] byteCode) {
        this.byteCode = byteCode;
    }

    public int nextU2toInt() {
        return Util.byteToInt(new byte[]{byteCode[index++], byteCode[index++]});
    }

    public int nextU2ToInt() {
        return nextU2toInt();
    }

    public int nextU1toInt() {
        return Util.byteToInt(new byte[]{byteCode[index++]});
    }

    public String nextU4toHex() {
        return Util.byteToHexString(new byte[]{byteCode[index++], byteCode[index++],
                byteCode[index++], byteCode[index++]});
    }

//    public byte[] getBytes(int len) {
//        byte[] data = new byte[len];
//        for (int i = 0; i < len; i++) {
//            data[i] = byteCode[index++];
//        }
//        return data;
//    }

    public String nextUxToHexString(int len) {
        byte[] tmp = new byte[len];

        for (int i = 0; i < len; i++) {
            tmp[i] = byteCode[index++];
        }
        return Util.byteToHexString(tmp).toLowerCase();
    }

    public int nextU4ToInt() {
        return Util.byteToInt(new byte[] { byteCode[index++], byteCode[index++], byteCode[index++], byteCode[index++] });
    }

    public byte[] getBytes(int len) {
        if (index + len >= byteCode.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        byte[] data = Arrays.copyOfRange(byteCode, index, index + len);
        index += len;
        return data;
    }

    public void back(int n) {
        this.index -= n;
    }
}
