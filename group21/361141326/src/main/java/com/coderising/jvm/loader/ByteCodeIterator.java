package com.coderising.jvm.loader;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ByteCodeIterator {

    private byte[] bytes;

    private int size;

    private int cursor;

    public ByteCodeIterator(byte[] bytes) {
        if (bytes == null) throw new IllegalArgumentException("bytes can't be null");
        this.bytes = bytes;
        this.size = bytes.length;
        this.cursor = 0;
    }

    public boolean hasNext() {
        return cursor < size;
    }

    public byte next() {
        int nextIndex = cursor + 1;
        if (nextIndex > size) {
            throw new NoSuchElementException("nextIndex:[" + nextIndex + "]");
        }

        return bytes[cursor++];
    }

    public byte[] next(int length) {
        int endIndex = cursor + length;
        if (endIndex > size) {
            throw new NoSuchElementException("index:[" + endIndex + "]");
        }

        byte[] result = Arrays.copyOfRange(this.bytes, cursor, endIndex);
        cursor += length;

        return result;
    }
}

