package com.bruce.homework0409.jvm.loader;

import com.bruce.homework0409.jvm.util.Util;

import java.util.Arrays;

public class ByteCodeIterator {
    private byte[] codes;
    private int pos = 0;

    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;
    }

    public byte[] getBytes(int len) {
        if (pos + len >= codes.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        byte[] array = Arrays.copyOfRange(codes, pos, pos + len);
        pos += len;
        return array;
    }

    public int nextU1ToInt() {
        return Util.byteToInt(new byte[] { codes[pos++] });
    }

    public int nextU2ToInt() {
        return Util.byteToInt(new byte[] { codes[pos++], codes[pos++] });
    }

    public int nextU4ToInt() {
        return Util.byteToInt(new byte[] { codes[pos++], codes[pos++], codes[pos++], codes[pos++] });
    }

    public String nextU4ToHexString() {
        return Util.byteToHexString(new byte[] { codes[pos++], codes[pos++], codes[pos++], codes[pos++] });
    }

    public String nextUxToHexString(int len) {
        if (len < 1) {
            return null;
        }
        byte[] array = new byte[len];
        for (int i = 0; i < len; i++) {
            array[i] = codes[pos++];
        }
        return Util.byteToHexString(array);
    }
}
