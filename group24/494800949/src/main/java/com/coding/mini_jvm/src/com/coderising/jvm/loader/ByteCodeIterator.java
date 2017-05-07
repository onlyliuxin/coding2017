package com.coding.mini_jvm.src.com.coderising.jvm.loader;

import com.coding.mini_jvm.src.com.coderising.jvm.util.Util;

public class ByteCodeIterator {
    public static final int U1 = 1;
    public static final int U2 = 2;
    public static final int U4 = 4;
    public static final int U8 = 8;
    private byte[] bytes;
    private int cursor;

    public ByteCodeIterator(byte[] bytes) {
        this.bytes = bytes;
    }

    public String readTwoBytesToString() {
        String ret = Util.byte2String(bytes, cursor, U2);
        cursor += U2;
        return ret;
    }

    public String readBytesToString(int len) {
        String ret = Util.byte2String(bytes, cursor, len);
        cursor += len;
        return ret;
    }


    public String readBytesToHexString(int len) {
        byte[] bs = new byte[len];
        System.arraycopy(bytes, cursor, bs, 0, len);
        String ret = Util.byteToHexString(bs);
        cursor += len;
        return ret;
    }


    public int readTwoBytesToInt() {
        int ret = Util.bytes2Int(bytes, cursor, U2);
        cursor += U2;
        return ret;
    }

    public int readFourBytesToInt() {
        int ret = Util.bytes2Int(bytes, cursor, U4);
        cursor += U4;
        return ret;
    }

    public int readByteToInt() {
        int ret = Util.bytes2Int(bytes, cursor, U1);
        cursor += U1;
        return ret;
    }




    public int skip(int len) {
        if (cursor + len < 0 || cursor + len > bytes.length - 1) {
            throw new IndexOutOfBoundsException();
        }
        cursor += len;
        return cursor;
    }

    public int back(int len) {
        if (cursor + len < 0 || cursor + len > bytes.length - 1) {
            throw new IndexOutOfBoundsException();
        }
        cursor -= len;
        return cursor;
    }
}
