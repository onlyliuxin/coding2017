package task3.download.impl;

import task3.download.api.Connection;

import java.io.IOException;
import java.io.InputStream;

public class ConnectionImpl implements Connection {

    private InputStream inputStream;

    public ConnectionImpl(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        byte[] buffer = new byte[endPos - startPos + 1];
        inputStream.read(buffer, 0, buffer.length);
        return buffer;
    }

    @Override
    public int getContentLength() {
        int length = 0;
        try {
            length = inputStream.available();
            System.out.println("接收到的数据长度为 " + length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return length;
    }

    @Override
    public void close() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
