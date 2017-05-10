package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

import java.util.Arrays;

public class ByteCodeIterator {
    private byte[] code;
    private int pos = 0;

    public ByteCodeIterator(byte[] code) {
        this.code = code;
    }

    public byte[] getBytes(int length) {
        if (pos + length >= code.length) {
            throw new IndexOutOfBoundsException("not enough bytes!");
        }
        byte[] bytes = Arrays.copyOfRange(code, pos, pos + length);
        pos += length;
        return bytes;
    }

    public int nextU1Int() {
        return Util.byteToInt(new byte[]{code[pos++]});
    }

    public int nextU2Int() {

        return Util.byteToInt(new byte[]{code[pos++], code[pos++]});
    }

    public int nextU4Int() {
        return Util.byteToInt(new byte[]{code[pos++], code[pos++], code[pos++], code[pos++]});
    }

    public String nextU4HexString() {
        return Util.byteToHexString(new byte[]{code[pos++], code[pos++], code[pos++], code[pos++]});
    }

    public String nextUxToHexString(int len) {
        byte[] tmp = new byte[len];

        for (int i = 0; i < len; i++) {
            tmp[i] = code[pos++];
        }
        return Util.byteToHexString(tmp).toLowerCase();

    }

    public void back(int n) {
        this.pos -= n;
    }
}
