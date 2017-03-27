package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.impl.ConnectionManagerImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadThread extends Thread {
    Connection conn;
    int startPos;
    int endPos;
    String url;
    public boolean finished = false;

    public DownloadThread(Connection conn, int startPos, int endPos, String url) {
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
        this.url = url;
    }

    public void run() {
        try {
            ConnectionManager cm = new ConnectionManagerImpl();
            conn = cm.open(url);
            byte[] result = conn.read(startPos, endPos);
            String fileName = url.substring(url.lastIndexOf('/') + 1);
            RandomAccessFile raf = new RandomAccessFile("D:" + File.separator + fileName, "rw");
            //移动指针至该线程负责写入数据的位置。
            raf.seek(startPos);
            raf.write(result, 0, result.length);
            raf.close();
            System.out.println("线程："+Thread.currentThread().getName()+"下载完成");
            finished = true;
        } catch (ConnectionException | IOException e) {
            e.printStackTrace();
        }
    }
}
