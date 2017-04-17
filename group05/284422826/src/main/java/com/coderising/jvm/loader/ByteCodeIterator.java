package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

import java.util.Arrays;


public class ByteCodeIterator {
    byte[] codes;
    int pos = 0;

    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;
    }

    public byte[] getBytes(int len) {
        if (pos + len >= codes.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        byte[] data = Arrays.copyOfRange(codes, pos, pos + len);
        pos = pos + len;
        return data;
    }

    public int nextU1toInt() {
        return Util.byteToInt(new byte[]{codes[pos++]});
    }

    public int nextU2toInt() {
        return Util.byteToInt(new byte[]{codes[pos++], codes[pos++]});
    }

    public int nextU4toInt() {
        return Util.byteToInt(new byte[]{codes[pos++], codes[pos++], codes[pos++], codes[pos++]});
    }

    public String nextU4ToHexString(){
        return Util.byteToHexString(new byte[]{codes[pos++], codes[pos++], codes[pos++], codes[pos++]});
    }

    public String nextUxToHexString(int len){
        byte[] temp = new byte[len];
        for (int i = 0; i < len; i++) {
            temp[i] = codes[pos++];
        }
        return Util.byteToHexString(temp).toLowerCase();
    }

    public void back(int n){
        pos = pos - n;
    }

}
