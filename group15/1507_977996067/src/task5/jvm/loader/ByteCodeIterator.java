package task5.jvm.loader;

import task5.jvm.util.Util;

import java.util.Arrays;

public class ByteCodeIterator {

    private int position;

    private byte[] bytes;

    public ByteCodeIterator(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getMagicNumber() {
        position = 0;
        byte[] bytes = Arrays.copyOf(this.bytes, 4);
        position += 4;
        return Util.byteToHexString(bytes);
    }

    public int next2Bytes() {
        return nextBytes(2);
    }

    public int nextFlag() {
        return nextBytes(1);
    }

    public byte[] getBytes(int length) {
        byte[] bytes = Arrays.copyOfRange(this.bytes, position, position + length);
        position += length;
        return bytes;
    }

    private int nextBytes(int size) {
        byte[] bytes = Arrays.copyOfRange(this.bytes, position, position + size);
        position += size;
        return Util.byteToInt(bytes);
    }
}
