package com.coding.week3.download1;

import com.coding.week3.download1.api.Connection;
import com.coding.week3.download1.api.ConnectionManager;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class DownloadTask implements Runnable {
    private static final int BUFF_SIZE = 1024;
    ConnectionManager connectionManager;
    int               startPos;
    int               endPos;
    RandomAccessFile  ras;
    AtomicInteger     totalDownloadBytesCount;
    AtomicInteger     eachThreadDownloadBytesCount;
    String            url;

    public DownloadTask(String url, ConnectionManager connectionManager, int startPos, int endPos, RandomAccessFile ras, AtomicInteger totalDownloadBytesCount) {
        this.url = url;
        this.connectionManager = connectionManager;
        this.startPos = startPos;
        this.endPos = endPos;
        this.ras = ras;
        this.totalDownloadBytesCount = totalDownloadBytesCount;
        this.eachThreadDownloadBytesCount = new AtomicInteger(0);
    }

    @Override
    public void run() {
        Connection conn = null;
        InputStream is = null;
        try {
            conn = connectionManager.open(url);
            is = getInputStream(conn);
            is.skip(startPos);
            ras.seek(startPos);
            byte[] bytes = new byte[BUFF_SIZE];
            int hasRead;
            int readTimes = (endPos - startPos) / BUFF_SIZE + 4;
            for (int i = 0; i < readTimes; i++) {
                hasRead = is.read(bytes);
                if (hasRead == -1) {
                    break;
                }
                ras.write(bytes, 0, hasRead);
                totalDownloadBytesCount.getAndAdd(hasRead);
                eachThreadDownloadBytesCount.getAndAdd(hasRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private InputStream getInputStream(Connection connection){
        Connection conn = null;
        InputStream is = null;
        try {
            conn = connectionManager.open(url);
            is = conn.getInputStream();
            return is;
        } catch (IOException e) {
            for (int i = 0; i < 5; i++) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    conn = connectionManager.open(url);
                    is = conn.getInputStream();
                    is.skip(startPos);
                    break;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            }
            throw new RuntimeException("连接超时", e);
        }

    }




    public int getEachThreadDownloadBytesCount() {
        return eachThreadDownloadBytesCount.get();
    }
}
