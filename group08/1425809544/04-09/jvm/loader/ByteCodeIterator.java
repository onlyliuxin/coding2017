package com.coderising.jvm.loader;

import org.jetbrains.annotations.NotNull;

import java.io.DataInput;
import java.io.IOException;

public class ByteCodeIterator implements DataInput{


    public void readFully(byte[] b) throws IOException {

    }

    public void readFully(byte[] b, int off, int len) throws IOException {

    }

    public int skipBytes(int n) throws IOException {
        return 0;
    }

    public boolean readBoolean() throws IOException {
        return false;
    }

    public byte readByte() throws IOException {
        return 0;
    }

    public int readUnsignedByte() throws IOException {

        return 0;
    }

    public short readShort() throws IOException {
        return 0;
    }

    public int readUnsignedShort() throws IOException {
        return 0;
    }

    public char readChar() throws IOException {
        return 0;
    }

    public int readInt() throws IOException {
        return 0;
    }

    public long readLong() throws IOException {
        return 0;
    }

    public float readFloat() throws IOException {
        return 0;
    }

    public double readDouble() throws IOException {
        return 0;
    }

    public String readLine() throws IOException {
        return null;
    }

    @NotNull
    public String readUTF() throws IOException {
        return null;
    }
}
