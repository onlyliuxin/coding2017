package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;
import java.util.Arrays;

public class ByteCodeIterator {
    private byte[] codes;
    private int pos;

    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;
    }

    public byte[] getBytes(int len) {
        if (pos + len >= codes.length) {
            throw new IndexOutOfBoundsException();
        }

        byte[] bytes = Arrays.copyOfRange(codes, pos, pos + len);
        pos += len;
        return bytes;
    }

    public int nextU1ToInt() {
        return Util.byteToInt(new byte[] {codes[pos++]});
    }

    public int nextU2ToInt() {
        return Util.byteToInt(new byte[] {codes[pos++], codes[pos++]});
    }

    public int nextU4ToInt() {
        return Util.byteToInt(new byte[] {codes[pos++], codes[pos++], codes[pos++], codes[pos++]});
    }

    public String nextU4ToHexString() {
        return Util.byteToHexString(new byte[] {codes[pos++], codes[pos++], codes[pos++], codes[pos++]});
    }

    public String newUxToHexString(int len) {
        String hexString = Util.byteToHexString(Arrays.copyOfRange(codes, pos, pos + len));
        pos += len;
        return hexString;
    }
}
