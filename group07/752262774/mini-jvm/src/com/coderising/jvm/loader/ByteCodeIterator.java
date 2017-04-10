package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ByteCodeIterator implements Iterator{
    private byte[] codes;
    private int pos;

    public ByteCodeIterator(byte[] codes) {
        this.codes = codes;
    }


    @Override
    public boolean hasNext() {
        return pos < codes.length;
    }

    @Override
    public Object next() {
        if (pos < codes.length) {
            return codes[pos++];
        }else {
            throw new NoSuchElementException();
        }
    }

    public boolean hasNext(int n) {
        return pos+n < codes.length;
    }

    public int nextUnToInt(int n) {
        byte[] codes = new byte[n];
        for (int i=0; i<n; i++) {
            codes[i] = (byte)next();
        }
        return Util.byteToInt(codes);
    }

    public int nextU2ToInt() {
        byte[] codes = new byte[2];
        codes[0] = (byte)next();
        codes[1] = (byte)next();
        return Util.byteToInt(codes);
    }

    public String nextU4ToHexString() {
        byte[] codes = new byte[4];
        for (int i=0; i<4; i++) {
            codes[i] = (byte)next();
        }
        return Util.byteToHexString(codes);
    }
}
