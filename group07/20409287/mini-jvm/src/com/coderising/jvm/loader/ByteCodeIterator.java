package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {

    private byte[] byteCode;

    private int index = 0;

    public ByteCodeIterator(byte[] byteCode) {
        this.byteCode = byteCode;
    }

    public int nextU2toInt() {
        return Util.byteToInt(new byte[]{byteCode[index++], byteCode[index++]});
    }

    public int nextU1toInt() {
        return Util.byteToInt(new byte[]{byteCode[index++]});
    }

    public String nextU4toHex() {
        return Util.byteToHexString(new byte[]{byteCode[index++], byteCode[index++],
                byteCode[index++], byteCode[index++]});
    }

    public byte[] getBytes(int len) {
        byte[] data = new byte[len];
        for (int i = 0; i < len; i++) {
            data[i] = byteCode[index++];
        }
        return data;
    }
}
