package com.coderising.jvm.loader;

public class ByteCodeIterator {

    private byte[] codes;

    private int index;

    public ByteCodeIterator(byte[] codes) {
        this.codes = new byte[codes.length];
        System.arraycopy(codes, 0, this.codes, 0, codes.length);
        index = 0;
    }

    public byte[] getNext(int len) {
        byte[] result = new byte[len];
        for (int i = 0; i < len && index < codes.length; i++, index++) {
            result[i] = codes[index];
        }
        return result;
    }

    public boolean hasNext() {
        return index <= codes.length;
    }

}
