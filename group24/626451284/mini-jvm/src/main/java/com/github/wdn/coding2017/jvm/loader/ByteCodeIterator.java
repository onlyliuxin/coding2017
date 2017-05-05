package com.github.wdn.coding2017.jvm.loader;


import com.github.wdn.coding2017.jvm.util.Util;

import java.nio.charset.Charset;

public class ByteCodeIterator {
    private byte[] bytes;
    private int index;

    public ByteCodeIterator(byte[] bytes){
        this.bytes = bytes;
    }

    public byte[] read(){
        return new byte[]{bytes[index++]};
    }
    public int readToInt(){
        return Util.byteToInt(new byte[]{bytes[index++]});
    }
    public int readU2ToInt(){
        return Util.byteToInt(new byte[]{bytes[index++],bytes[index++]});
    }
    public String readU2ToString(){
        return Util.byteToHexString(new byte[]{bytes[index++],bytes[index++]});
    }
    public int readU4ToInt(){
        return Util.byteToInt(new byte[]{bytes[index++],bytes[index++],bytes[index++],bytes[index++]});
    }
    public String readU4ToString(){
        return Util.byteToHexString(new byte[]{bytes[index++],bytes[index++],bytes[index++],bytes[index++]});
    }
    public String readCustomToString(int len){
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[i] = bytes[index++];
        }
        return new String(b);
        //return Util.byteToHexString(b);
    }
}
