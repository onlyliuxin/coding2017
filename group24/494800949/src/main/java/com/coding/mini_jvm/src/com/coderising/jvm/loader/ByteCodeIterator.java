package com.coding.mini_jvm.src.com.coderising.jvm.loader;

import com.coding.mini_jvm.src.com.coderising.jvm.util.Util;

public class ByteCodeIterator {
    private static final int U1 = 1;
    private static final int U2 = 2;
    private static final int U4 = 4;
    private static final int U8 = 8;
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


    public int readTwoBytesToInt() {
        int ret = Util.bytes2Int(bytes, cursor, U2);
        cursor += U2;
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
}
