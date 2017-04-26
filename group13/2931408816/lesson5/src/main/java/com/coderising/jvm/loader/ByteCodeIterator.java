package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
    private int pos = 0;
    private byte[] code;

    public ByteCodeIterator(byte[] code) {
        this.code = code;
    }

    public String nextU4ToHexString(){
        byte[] bytes=nextByte(4);
        return Util.byteToHexString(bytes);
    }
    public int nextU2ToInt(){
        byte[] bytes = nextByte(2);
        return Util.byteToInt(bytes);
    }
    public int nextU1ToInt(){
        byte[] bytes = nextByte(1);
        return Util.byteToInt(bytes);
    }
    public String nextToString(int len){
        byte[] bytes = nextByte(len);
        return new String(bytes);
    }
    private byte[] nextByte(int len){
        byte[] bytes = new byte[len];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i]=code[pos+i];
        }
        pos+=len;
        return bytes;
    }
}
