package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

import java.util.Arrays;

public class ByteCodeIterator {
    private byte[] codes;
    private int pos = 0;
    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;
    }

    public String nextU4ToHexString() {
        return Util.byteToHexString(new byte[] {codes[pos++],codes[pos++],codes[pos++],codes[pos++]});
    }

    public int nextU2ToInt() {
        return Util.byteToInt(new byte[]{ codes[pos++] ,codes[pos++]});
    }

    public int nextU1ToInt() {
        return Util.byteToInt(new byte[] { codes[pos++]});
    }


    public int nextU4ToInt(){return Util.byteToInt(new byte[]{codes[pos++],codes[pos++],codes[pos++],codes[pos++]});}


    public String nextUxToHexString(int len) {
        byte[] tmp = new byte[len];

        for (int i = 0; i < len; i++) {
            tmp[i] = codes[pos++];
        }
        return Util.byteToHexString(tmp).toLowerCase();

    }

    public void back(int n) {
        this.pos -= n;
    }

    public byte[] getByte(int len) {
        if(len + pos >= codes.length){
            throw new IndexOutOfBoundsException();
        }
        byte[] data = Arrays.copyOfRange(codes,pos,pos+len);
        pos += len;
        return data;
    }
}
