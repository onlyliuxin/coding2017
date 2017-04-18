package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
    private byte[] codes;
    private int pos;

    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;
        this.pos = 0;
    }

    public byte[] getCodes(int length) {
        if (this.pos + length >= this.codes.length) {
            throw new IndexOutOfBoundsException();
        }

        byte[] buffer = new byte[length];
        for (int i = 0; i < length; i++) {
            buffer[i] = this.codes[pos++];
        }
        return buffer;
    }

    public int nextU1ToInt() {
        return Util.byteToInt(new byte[]{codes[pos++]});
    }

    public int nextU2ToInt() {
        return Util.byteToInt(new byte[]{codes[pos++], codes[pos++]});
    }

    public int nextU4ToInt() {
        return Util.byteToInt(new byte[]{codes[pos++], codes[pos++], codes[pos++], codes[pos++]});
    }

    public String nextU4ToHexString() {
        return Util.byteToHexString(new byte[]{codes[pos++], codes[pos++], codes[pos++], codes[pos++]});
    }

    public String nextUxToHexString(int len) {
        byte[] buffer = new byte[len];
        for (int i = 0; i < len; i++) {
            buffer[i] = this.codes[pos++];
        }
        return Util.byteToHexString(buffer);
    }

    public void back(int i) {
        this.pos -= i;

    }
}
