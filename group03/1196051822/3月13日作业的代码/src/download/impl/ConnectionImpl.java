package com.coderising.download.impl;

import java.io.*;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {

    private InputStream inputStream;
    private BufferedInputStream bufferedInputStream;
    private int contentLength;
    public ConnectionImpl(InputStream isr,int contentLength) {
        this.inputStream = isr;
        this.contentLength = contentLength;
    }

    byte[] bytes = new byte[]{};

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        bufferedInputStream = new BufferedInputStream(inputStream);
        bytes = new byte[1];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int pos = endPos;
        bufferedInputStream.skip(startPos);
        while (pos-- > 0) {
            int flag = bufferedInputStream.read(bytes);
            if (flag != -1) {
                baos.write(bytes);
            }
        }
        //单线程读取
//        int flag = 0;
//        flag = bufferedInputStream.read(bytes,0,(endPos - startPos));
//        if (flag != -1){
//            return bytes;
//        }
        return baos.toByteArray();
    }

    @Override
    public int getContentLength() {
        return contentLength;
    }

    @Override
    public void close() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (bufferedInputStream != null) {
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
