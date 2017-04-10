package com.donaldy.jvm.loader;

import com.donaldy.jvm.util.Util;

public class ByteCodeIterator {

    private byte[] codes;

    private int pointer = 0;

    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;
    }

    public String nextU4ToHexString() {
        byte [] byteCodes = nextLenByte(4);

        return Util.byteToHexString(byteCodes);
    }

    public int nextU2ToInt() {
        byte [] byteCodes = nextLenByte(2);

        return Util.byteToInt(byteCodes);
    }

    public int nextU1toInt() {
        byte [] byteCodes = nextLenByte(1);

        return Util.byteToInt(byteCodes);
    }

    public byte[] getBytes(int len) {
        byte [] byteCodes = nextLenByte(len);

        return byteCodes;
    }

    private byte[] nextLenByte(int len) {
        if (this.pointer + len >= this.codes.length)
            throw new IndexOutOfBoundsException("codes.length : " + this.codes.length);

        byte [] byteCodes = new byte[len];

        for (int i = 0 ; i < len; ++i) {
            byteCodes[i] = this.codes[pointer ++];
        }

        return byteCodes;
    }
}
