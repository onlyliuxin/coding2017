package com.coderising.jvm.loader;



public class ByteCodeIterator {



    private byte[] bytes;

    private int index;

    public ByteCodeIterator(byte[] bytes) {
        this.bytes = new byte[bytes.length];
        System.arraycopy(bytes,0,this.bytes,0,bytes.length);
        this.index =  0;
    }

    public byte[] getNext(int length){
        byte[] newBytes = new byte[length];
        for (int i = 0; i < length && index<bytes.length; i++,index++) {
            newBytes[i] = bytes[index];
        }
        return newBytes;
    }

    public boolean hasNext() {
        return index<=bytes.length;
    }



}
