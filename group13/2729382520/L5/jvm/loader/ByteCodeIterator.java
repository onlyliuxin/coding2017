package io.github.vxzh.jvm.loader;

import io.github.vxzh.jvm.util.Util;

import java.util.Arrays;

public class ByteCodeIterator {
    private int pos = 0;
    private byte[] code;

    public ByteCodeIterator(byte[] code) {
        this.code = code;
    }

    public String nextU4ToHexString() {
        byte[] bytes = nextBytes(4);
        return Util.byteToHexString(bytes);
    }

    public int nextU2ToInt() {
        byte[] bytes = nextBytes(2);
        return Util.byteToInt(bytes);
    }

    public int nextU1ToInt() {
        byte[] bytes = nextBytes(1);
        return Util.byteToInt(bytes);
    }

    public String nextBytesToString(int len) {
        byte[] bytes = nextBytes(len);
        return new String(bytes);
    }

    private byte[] nextBytes(int len) {
        byte[] bytes = Arrays.copyOfRange(code, pos, pos+len);
        pos += len;
        return bytes;
    }

}