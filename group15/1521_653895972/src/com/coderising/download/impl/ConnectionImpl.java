package com.coderising.download.impl;

import com.coderising.download.api.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class ConnectionImpl implements Connection {
    private HttpURLConnection conn;
    private InputStream is;

    public ConnectionImpl() {
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        //设置读取的文件块
        is = conn.getInputStream();
        byte[] data = new byte[endPos-startPos];
        int len_tmp = endPos-startPos;
        int index=0;
        int readSize;
        while ((readSize = is.read(data,index,len_tmp)) != -1) {
            len_tmp -= readSize;
            if (len_tmp == 0) {
                break;
            }
            index = index + readSize;
        }
        return data;
    }

    @Override
    public int getContentLength() {
        return conn.getContentLength();
    }

    @Override
    public void close() {
        try {
            if (is != null)
                is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setConn(HttpURLConnection conn) {
        this.conn = conn;
    }
}
