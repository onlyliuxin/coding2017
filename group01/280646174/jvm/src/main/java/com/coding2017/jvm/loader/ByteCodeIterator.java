package com.coding2017.jvm.loader;

import com.coding2017.jvm.util.ByteUtil;

public class ByteCodeIterator {

    private byte[] data;
    private int pos;

    public ByteCodeIterator(byte[] data) {
        this.data = data;
        pos = 0;
    }

    public void skip(int n) {
        pos += n;
    }

    public int nextU1ToInt() {
        return nextByteN(1)[0];
    }

    public int nextU2ToInt() {
        return ByteUtil.bytesToInt(nextByteN(2));
    }

    public String nextU4ToString() {
        return ByteUtil.byteToHexString(nextByteN(4));
    }

    public byte[] nextByteN(int n) {
        byte[] bytes = new byte[n];
        for (int i = 0; i < n; i++) {
            bytes[i] = data[pos++];
        }
        return bytes;
    }
}
