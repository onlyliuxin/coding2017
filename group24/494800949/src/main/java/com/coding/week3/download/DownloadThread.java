package com.coding.week3.download;


import com.coding.week3.download.api.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadThread extends Thread {
    private static final int BUFF_SIZE = 1024;
    Connection       conn;
    int              startPos;
    int              endPos;
    RandomAccessFile ras;
    AtomicInteger    downloadBytesCount;

    public DownloadThread(Connection conn, int startPos, int endPos) {
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public DownloadThread(Connection conn, int startPos, int endPos, RandomAccessFile ras, AtomicInteger atomicLong) {
        this(conn, startPos, endPos);
        this.ras = ras;
        this.downloadBytesCount = atomicLong;
    }

    public void run() {
        try {
            InputStream is = conn.getInputStream();
            is.skip(startPos);
            ras.seek(startPos);
            byte[] bytes = new byte[BUFF_SIZE];
            int hasRead = 0;
            int readTimes = (endPos - startPos) / BUFF_SIZE + 4;
            for (int i = 0; i < readTimes; i++) {
                hasRead = is.read(bytes );
                if (hasRead == -1) {
                    break;
                }
                ras.write(bytes, 0, hasRead);
                downloadBytesCount.getAndAdd(hasRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
