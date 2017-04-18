package com.coderising.jvm.loader;


import com.coderising.jvm.util.BytesIterUtil;

import java.util.Arrays;
import java.util.Timer;

public class ByteCodeIterator {



    private byte[] bytes;

    private int pos =0;

    public ByteCodeIterator(byte[] bytes) {
        this.bytes = bytes;
    }


    public byte[] getBytes(int len) {
        if (pos + len >= bytes.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        byte[] data = Arrays.copyOfRange(bytes, pos, pos + len);
        pos+=len;
        return data;
    }


    public int nextU1ToInt() {
        return BytesIterUtil.byteToInt(new byte[]{bytes[pos++]});
    }

    public int nextU2ToInt() {
        return BytesIterUtil.byteToInt(new byte[]{bytes[pos++], bytes[pos++]});
    }

    public int nextU4ToInt() {
        return BytesIterUtil.byteToInt(new byte[]{bytes[pos++], bytes[pos++], bytes[pos++], bytes[pos++]});
    }


    public String nextU1ToHexString() {
        return BytesIterUtil.byteToHexString(new byte[]{bytes[pos++]});
    }

    public String nextU2ToHexString() {
        return BytesIterUtil.byteToHexString(new byte[]{bytes[pos++], bytes[pos++]});
    }

    public String nextU4ToHexString() {
        return BytesIterUtil.byteToHexString(new byte[]{bytes[pos++], bytes[pos++], bytes[pos++], bytes[pos++]});
    }

    public String nextUxToHexString(int len) {
        byte [] temp = new byte[len];
        for (int i = 0; i < len; i++) {
            temp[i] = bytes[pos++];
        }
        return BytesIterUtil.byteToHexString(temp).toLowerCase();
    }

    public void back(int n) {
        this.pos -=n;
    }




    //这个实现不太好
    public byte[] getNext(int length){
        byte[] newBytes = new byte[length];
        for (int i = 0; i < length && pos<bytes.length; i++,pos++) {
            newBytes[i] = bytes[pos];
        }
        return newBytes;
    }

    public boolean hasNext() {
        return pos<=bytes.length;
    }



}
