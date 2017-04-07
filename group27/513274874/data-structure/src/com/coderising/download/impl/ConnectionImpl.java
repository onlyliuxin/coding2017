package com.coderising.download.impl;

import com.coderising.download.api.Connection;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionImpl implements Connection {
    private URL url;

    // 定义字节数组（取水的竹筒）的长度
    private final int BUFF_LEN = 32;

    // 下载资源对应的输入流
    private InputStream is;


    ByteArrayOutputStream bos;

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {

        this.is = url.openStream();

        is.skip(startPos);
        // 定义读取输入流内容的的缓存数组（竹筒）
        byte[] buff = new byte[BUFF_LEN];
        // 本线程负责下载资源的大小
        long contentLen = endPos - startPos;
        bos = new ByteArrayOutputStream((int) contentLen);
        BufferedInputStream in =  new BufferedInputStream(is);
        int len = 0;
        while (-1 != (len = in.read(buff, 0, BUFF_LEN))) {
            bos.write(buff, 0, len);
        }
        return bos.toByteArray();
    }
//    @Override
//    public byte[] read(int startPos, int endPos) throws IOException {
//        raf = new RandomAccessFile("newfile.jpg", "rw");
//        this.is = url.openStream();
//
//        is.skip(startPos);
//        raf.seek(startPos);
//        // 定义读取输入流内容的的缓存数组（竹筒）
//        byte[] buff = new byte[BUFF_LEN];
//        // 本线程负责下载资源的大小
//        long contentLen = endPos - startPos;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) contentLen);
//        // 定义最多需要读取几次就可以完成本线程的下载
//        long times = contentLen / BUFF_LEN + 4;
//        // 实际读取的字节数
//        int hasRead = 0;
//        for (int i = 0; i < times; i++) {
//            hasRead = is.read(buff);
//            // 如果读取的字节数小于0，则退出循环！
//            if (hasRead < 0) {
//                break;
//            }
//            raf.write(buff, 0, hasRead);
//        }
//
//        return null;
//    }

    @Override
    public int getContentLength() {
        int length = 0;
        // 打开该URL对应的URLConnection
        URLConnection con = null;
        try {
            con = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取连接URL资源的长度
        length = con.getContentLength();
        return length;
    }

    @Override
    public void close() {
        try {
            if (is != null) {
                is.close();
            }
            if (bos != null) {
                bos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ConnectionImpl(URL url) {
        this.url = url;
    }
}
